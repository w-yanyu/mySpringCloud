package com.example.common.comm.page.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.comm.page.PageQuery;


/**
 * @Author yanyu
 * @Date 2024/5/6 22:44
 * @Version 1.0
 * @Description 分页处理
 **/
public interface SqlPageWrapper<T> {

    QueryWrapper<T> getWrapper(PageQuery<T> pageQuery);
}
