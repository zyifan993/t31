package com.zyf.t31.auth.serviceauth.service.impl;

import com.zyf.t31.admin.po.Role;
import com.zyf.t31.auth.serviceauth.client.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    UserClient userClient;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.zyf.t31.admin.po.User user = userClient.getUserName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            logger.debug("current user = " + user);
            // 获取用户的授权
            List<Role> roles = userClient.selectRolesByUserId(user.getId());
            // 声明授权文件
            for (Role role : roles) {
                if (role != null && role.getName() != null) {
                    // spring Security中权限名称必须满足ROLE_XXX
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
        }
        logger.debug("granted authorities = " + grantedAuthorities);
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}

