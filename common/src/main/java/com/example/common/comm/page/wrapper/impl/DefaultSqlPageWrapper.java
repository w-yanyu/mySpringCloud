package com.example.common.comm.page.wrapper.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.comm.page.PageQuery;
import com.example.common.comm.page.Sorter;
import com.example.common.comm.page.wrapper.SqlPageWrapper;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName DefaultSqlPageWrapper
 * @Author yanyu
 * @Date 2024/5/6 22:44
 * @Version 1.0
 * @Description 默认分页处理
 **/
public class DefaultSqlPageWrapper<T> extends QueryWrapper<T> implements SqlPageWrapper<T> {
    @Override
    public QueryWrapper<T> getWrapper(PageQuery<T> pageQuery) {
        List<Sorter.SortKey> sortKeys = pageQuery.getSortKeys();
        QueryWrapper<T> wrapper = new DefaultSqlPageWrapper<T>();
        sortKeys.forEach(a -> {
            wrapper.orderBy(true, a.isAsc(), a.getKey());
        });
        return wrapper;
    }

    @Override
    public QueryWrapper<T> eq(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.eq(condition, column, val);
    }

    @Override
    public QueryWrapper<T> ne(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.ne(condition, column, val);
    }

    @Override
    public QueryWrapper<T> gt(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.gt(condition, column, val);
    }

    @Override
    public QueryWrapper<T> ge(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.ge(condition, column, val);
    }

    @Override
    public QueryWrapper<T> lt(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.lt(condition, column, val);
    }

    @Override
    public QueryWrapper<T> le(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.le(condition, column, val);
    }

    @Override
    public QueryWrapper<T> like(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.like(condition, column, val);
    }

    @Override
    public QueryWrapper<T> notLike(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.notLike(condition, column, val);
    }

    @Override
    public QueryWrapper<T> likeLeft(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.likeLeft(condition, column, val);
    }

    @Override
    public QueryWrapper<T> likeRight(String column, Object val) {
        boolean condition = !Objects.isNull(val);
        return super.likeRight(condition, column, val);
    }

    @Override
    public QueryWrapper<T> between(String column, Object val1, Object val2) {
        boolean condition = true;
        if (Objects.isNull(val1) && Objects.isNull(val2)) {
            condition = false;
        } else if (Objects.isNull(val1)) {
            return super.lt(true, column, val2);
        } else if (Objects.isNull(val2)) {
            return super.ge(true, column, val1);
        }
        return super.between(condition, column, val1, val2);
    }

    @Override
    public QueryWrapper<T> notBetween(String column, Object val1, Object val2) {
        boolean condition = true;
        if (Objects.isNull(val1) && Objects.isNull(val2)) {
            condition = false;
        } else if (Objects.isNull(val1)) {
            return super.lt(true, column, val2);
        } else if (Objects.isNull(val2)) {
            return super.ge(true, column, val1);
        }
        return super.notBetween(true, column, val1, val2);
    }

}
