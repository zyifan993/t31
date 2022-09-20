package com.zyf.t31.order.client;

import com.zyf.t31.admin.vo.MessageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "admin-service")
public interface NoticeClient {

    @PostMapping("/notice")
    void notice(@RequestBody MessageVO messageVO);
}
