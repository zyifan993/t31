package com.zyf.t31.order.client;

import com.zyf.t31.ticket.po.Passenger;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ticket-service")
public interface PassengerClient {

    @GetMapping(value = "/passenger/{id}")
    public Passenger getById(@PathVariable Long id);
}
