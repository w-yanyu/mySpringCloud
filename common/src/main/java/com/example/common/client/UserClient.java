package com.example.common.client;

import com.example.common.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author yanyu
 * @Date 2024/5/9 23:05
 * @Version 1.0
 * @Description TODO
 **/
@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/getUser/{id}")
    public User getUserById(@PathVariable("id") String id);
}
