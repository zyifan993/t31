package com.zyf.t31.ticket.client;

import com.zyf.t31.admin.api.UserApi;
import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "admin-service", fallback = UserClient.UserClientFallback.class)
public interface UserClient extends UserApi {

    @Component
    @RequestMapping("/fallback")
    class UserClientFallback implements UserClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(UserClientFallback.class);


        @Override
        public User getUserName(String userName) {
            return null;
        }

        @Override
        public List<Role> selectRolesByUserId(Long id) {
            LOGGER.info("异常发生，进入fallback方法");
            return null;
        }

        @Override
        public void addPoint(Long point, String username) {
            LOGGER.info("异常发生，进入fallback方法");
        }
    }

}