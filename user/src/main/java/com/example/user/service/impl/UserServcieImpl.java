package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.comm.page.PageQuery;
import com.example.common.entity.User;

import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserServcieImpl
 * @Author yanyu
 * @Date 2024/5/4 9:48
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@Service
@Transactional
public class UserServcieImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public static void main(String[] args) {
        //System.out.println(a());
        //System.out.println(b("1"));
        //System.out.println(b(null));
        int a = 1;
        assert a == 2:"Age must be positive";
        assert a == 1:"Age must be positive111";
        System.out.println(a);
        Constructor<UserServcieImpl> primaryConstructor = BeanUtils.findPrimaryConstructor(UserServcieImpl.class);
        System.out.println(primaryConstructor.getName());
    }


    private static String a(){

        System.out.println("1");
        return null;
    }

    private static String b(@NonNull String s){

        System.out.println(s);
        return null;
    }

    @Override
    public boolean save(User user) {
        //int insert = userDao.insert(user);
        int insert = userMapper.insert(user);
        return insert == 1;
    }

    @Override
    public User getUser(String id) {
        print();
        log.debug("任务执行后。。。");
        return userMapper.selectById(id);
    }

    @Async("myThreadPool")
    protected void print(){
        log.debug("任务执行开始。。。");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("任务异常执行",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<User> queryUsersPage(PageQuery<User> query) {
        // 查询条件
        User queryData = query.getQueryData();
        // 初始化分页数
        int pageNo = query.getPageNo();
        int pageSize = query.getPageSize();

        try {
            QueryWrapper<User> wrapper = query.getWrapper();
            Page<User> page = query.getPage();
            if(queryData != null){
                wrapper.eq("id",queryData.getId())
                        .like("user_name",queryData.getUsername())
                        .like("email",queryData.getEmail());
            }
            return userMapper.selectPage(page, wrapper);
        }catch (Exception e){
            log.error("unknown exception when using database", e);
            throw new RuntimeException(e);
        }
    }

}
