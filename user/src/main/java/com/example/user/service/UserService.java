package com.example.user.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.comm.page.PageQuery;
import com.example.common.entity.User;

/**
 * @ClassName UserService
 * @Author yanyu
 * @Date 2024/5/4 9:48
 * @Version 1.0
 * @Description TODO
 **/
public interface UserService {

    boolean save (User user);

    User getUser(String id);

    Page<User> queryUsersPage(PageQuery<User> query);
}
