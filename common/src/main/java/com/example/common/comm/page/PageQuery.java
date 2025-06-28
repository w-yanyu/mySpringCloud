package com.example.common.comm.page;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.comm.page.wrapper.SqlPageWrapper;
import com.example.common.comm.page.wrapper.impl.DefaultSqlPageWrapper;
import lombok.*;

/**
 * @ClassName PageQuery
 * @Author yanyu
 * @Date 2024/5/6 21:43
 * @Version 1.0
 * @Description TODO
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class PageQuery<T> extends Sorter {

    /**
     * 当前页数（默认）
     */
    private int pageNo = 1;

    /**
     * 每页记录数（默认）
     */
    private int pageSize = 10;

    /**
     * 分页查询条件
     */
    private T queryData;

    /**
     * mapper执行分页对象
     */
    @Setter(AccessLevel.NONE)
    private Page<T> page;

    /**
     * mapper执行查询条件对象
     */
    @Setter(AccessLevel.NONE)
    private Wrapper<T> wrapper;

    @Getter(AccessLevel.NONE)
    private SqlPageWrapper<T> sqlPageWrapper;

    public Page<T> getPage() {
        if (page == null) {
            this.page = new Page<>(pageNo, pageSize);
        }
        return page;
    }

    public QueryWrapper<T> getWrapper() {
        if (sqlPageWrapper == null)
            sqlPageWrapper = new DefaultSqlPageWrapper<T>();
        return sqlPageWrapper.getWrapper(this);
    }
}
