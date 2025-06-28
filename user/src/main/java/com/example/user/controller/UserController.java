package com.example.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.annotation.WebLog;
import com.example.common.comm.page.PageQuery;
import com.example.common.entity.User;
import com.example.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * @Author yanyu
 * @Date 2024/5/4 9:47
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @WebLog
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @WebLog
    @RequestMapping(method = RequestMethod.GET, value = "/getUser/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @WebLog
    @RequestMapping(method = RequestMethod.GET, value = "/getUser1")
    public User getUser1(@RequestParam(value = "id", required = false) String id) {
        return userService.getUser(id);
    }

    @WebLog
    @PostMapping("/getUsersByPage")
    public Page<User> getUsersByPage(@RequestBody PageQuery<User> userPageQuery){
        log.info("heheheheheheh");
        return userService.queryUsersPage(userPageQuery);
    }
}
