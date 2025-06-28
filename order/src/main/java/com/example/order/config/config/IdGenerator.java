package com.example.order.config.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName IdGenerator
 * @Author yanyu
 * @Date 2024/5/6 13:48
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@Deprecated
public class IdGenerator implements IdentifierGenerator {

    //@Value("${config.workerId}")
    private long workerId = 4L;

    //@Value("${config.dataCenterId}")
    private long dataCenterId = 3L;

    /*@Override
    //此方法生成序列，依赖数据库，需实现IKeyGenerator接口
    public String executeSql(String incrementerName) {
        log.info("mybatis plus keyGenerator: " + incrementerName + "(" + workerId + "," + dataCenterId + ")");
        // 此算法为雪花算法
        Sequence sequence = new Sequence(workerId,dataCenterId);
        long seq = sequence.nextId();
        String uid = "xx"+seq;
        return "select " + uid + " from dual";
    }
    @Override
    public DbType dbType() {
        return null;
    }*/

    @Override
    public Number nextId(Object entity) {
        // 此算法为雪花算法
        Sequence sequence = new Sequence(workerId,dataCenterId);
        long seq = sequence.nextId();
        log.info("生成序列号为：{}",seq);
        String s = String.valueOf(seq);
        int length = s.length();
        // Long 范围为-9223372036854775808~9223372036854775807，10进制为19位
        String uid = s.substring(0,length-3)+"000";
        //String uid = s.substring(0,length-3)+"000";
        return Long.valueOf(uid);
    }
}
