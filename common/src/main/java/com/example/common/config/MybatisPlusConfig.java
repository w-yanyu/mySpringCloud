package com.example.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName MybatisPlusConfig
 * @Author yanyu
 * @Date 2024/5/6 16:39
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
//@Configuration
@MapperScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.dao.*"})
@ComponentScan("com.gitee.sunchenbin.mybatis.actable.manager.*")
public class MybatisPlusConfig {

    //@Value("${config.workerId}")
    private long workerId = 4L;

    //@Value("${config.dataCenterId}")
    private long dataCenterId = 3L;

    @Value("${mybatis.database.type}")
    private String databaseType;

    /**
     * 配置项只能用逗号分隔
     * @Value("#{'${common.list}'.split(',')}")，这个可以指定分隔符
     */
    @Value("${mybatis-plus.mapper-locations}")
    private List<String> mapperLocationPatterns;

    /**
     * id生成算法
     * @return
     */
    /*@Bean
    public IdentifierGenerator idGenerator(){
        return new IdentifierGenerator() {
            @Override
            public Number nextId(Object entity) {
                // 此算法为雪花算法
                log.info("workerId:{},dataCenterId:{}",workerId,dataCenterId);
                Sequence sequence = new Sequence(workerId,dataCenterId);
                return sequence.nextId();
            }
        };
    }*/

    /**
     * 引入分页插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor  mybatisPlusInterceptor() {
        PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
        // 设置数据库类型
        innerInterceptor.setDbType(DbType.getDbType(databaseType));
        // 设置是否开启分页溢出处理功能
        innerInterceptor.setOverflow(true);
        // 设置是否开启 SQL 查询的优化功能
        innerInterceptor.setOptimizeJoin(true);
        // 设置最大分页限制，最多一页1000条
        innerInterceptor.setMaxLimit(1000L);
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(innerInterceptor);
        return interceptor;
    }

    /**
     * 字段新增、更新自动补充
     * @return
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                Date date = new Date();
                this.setFieldValByName("createTime", date, metaObject);
                this.setFieldValByName("updateTime", date, metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }
        };
    }
}
