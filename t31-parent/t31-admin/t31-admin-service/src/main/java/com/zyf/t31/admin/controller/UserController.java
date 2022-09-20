package com.zyf.t31.admin.controller;
import com.zyf.t31.admin.po.Menu;
import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.admin.service.IMenuService;
import com.zyf.t31.admin.service.IUserService;
import com.zyf.t31.admin.utils.BPwdEncoderUtil;
import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.core.vo.ResponseBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<IUserService, User> {


    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/login")
    public ResponseEntity<OAuth2AccessToken> login(String username, String password) {
        // 1:验证用户
        User user = service.getUserByUserName(username);
        if (null == user) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!BPwdEncoderUtil.matches(password, user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        // 2:使用restTemplate发送请求到授权服务器，申请令牌
        // 请求头 "basic auth"
        String client_secret = oAuth2ClientProperties.getClientId() + ":" + oAuth2ClientProperties.getClientSecret();
        client_secret = "Basic " + Base64.getEncoder().encodeToString(client_secret.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", client_secret);

        //请求参数
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList(username));
        map.put("password", Collections.singletonList(password));
        map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));
        map.put("scope",oAuth2ProtectedResourceDetails.getScope());

        // HttpEntity(请求参数，头。。。)
        HttpEntity httpEntity = new HttpEntity(map, headers);

        return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST, httpEntity, OAuth2AccessToken.class);

    }


    @ApiOperation("通过登录获得用户")
    @GetMapping("/get/{userName}")
    public User getByUserName(@PathVariable("userName") String userName) {
        return service.getUserByUserName(userName);
    }

    @ApiOperation("通过用户ID获得角色")
    @GetMapping("/select-roles/{id}")
    public List<Role> selectRolesByUserId(@PathVariable("id") Long id) {
        return service.selectRoleByUser(id);
    }

    @ApiOperation("通过当前用户得到用户的动态路由，生成菜单")
    @GetMapping("/select-user-menu")
    public List<Menu> selectMenuByLoginUser(Authentication authentication) {
        User user = service.getUserByUserName(authentication.getName());
        List<Menu> menus = menuService.listByUser(user.getId());
        // 部分子节点选中时，父节点不选中，找到后返回
        Set<Menu> result = new HashSet<>();
        menus.forEach((item) -> {
            if (item.getParentId() != null &&  menus.stream().noneMatch((it) -> item.getParentId().equals(it.getId()))) {
                Menu menu = menuService.getById(item.getParentId());
                result.add(menu);
            }
        });
        System.out.println(result.isEmpty());
        menus.addAll(result);
        return menus;
    }

    /**
     * 验证用户名是否存在
     */
    @ApiOperation("验证用户名是否存在")
    @PostMapping("/validate-name/{userName}")
    public String validUserName(@PathVariable String userName,  Long id) {
        long rowCount = service.findCountByUserName(userName);

        // 修改时=原来的用户名
        if (id != null) {
            User user = service.getById(id);
            if (null != userName && userName.equals(user.getUserName())) {
                return "{\"success\": true}";
            }
        }

        if (rowCount > 0) {
            return "{\"success\": false}";
        } else {
            return "{\"success\": true}";
        }
    }

    /**
     * 锁定用户
     */
    @GetMapping("/lock/{id}")
    @ApiOperation("锁定账户")
    public ResponseBean lock(@PathVariable Long id) throws Exception {
        ResponseBean rm = new ResponseBean();
        try {
            User u = service.getById(id);

            User user = new User();
            user.setId(id);
            if (null != u.getLock() && u.getLock()) {
                rm.setMsg("用户已解锁");
                user.setLock(false);
            } else {
                rm.setMsg("用户已锁定");
                user.setLock(true);
            }
            service.updateById(user);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setSuccess(false);
            rm.setMsg("保存失败");
        }

        return rm;
    }

    @Override
    public void afterEdit(User domain) {
        // 生成角色列表, 如：1,3,4
        List<Role> roles = service.selectRoleByUser(domain.getId());
        Long[] ids = new Long[roles.size()];
        for (int i=0; i< roles.size(); i++) {
            ids[i] = roles.get(i).getId();
        }
        domain.setRoleIds(ids);
    }

    /**
     * 增加，修改
     */
    @ApiOperation(value="用户注册", notes="注册普通会员")
    @PostMapping("/register")
    public ResponseBean register(User user) throws Exception {
        ResponseBean rm = new ResponseBean();
        try {
            //TODO 普通用户角色暂时写死
            user.setRoleIds(new Long[] {3L});
            service.saveOrUpdate(user);
            rm.setModel(user);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setSuccess(false);
            rm.setMsg("保存失败");
        }
        return rm;
    }

    /**
     * 加载当前用户信息
     * @param authentication
     * @return
     * @throws Exception
     */
    @ApiOperation(value="加载当前用户信息", notes="加载当前用户信息")
    @GetMapping("/self")
    public User self(Authentication authentication) throws Exception {
        return service.getUserByUserName(authentication.getName());
    }

}

