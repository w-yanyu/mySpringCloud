package com.example.common.aspect;

import com.example.common.property.FileProperties;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @ClassName WebLogAspectHandler
 * @Author yanyu
 * @Date 2024/4/26 10:39
 * @Version 1.0
 * @Description 注解@WebLog 解析的切面类
 **/
@Slf4j
@Aspect
@Component
public class WebLogAspectHandler {

    @Autowired
    private FileProperties fileProperties;

    @Pointcut("@annotation(com.example.common.annotation.WebLog)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("获取自定义配置属性，{}，{}",fileProperties.getName(),fileProperties.getExtension());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        if (request == null) {
            log.info("本次请求为空");
        } else {
            log.info("请求地址 : " + request.getRequestURL().toString());
            log.info("HTTP METHOD : " + request.getMethod());
        }

        log.info("CLASS_METHOD : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        log.info("参数 : " + Arrays.toString(pjp.getArgs()));
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        log.info("返回值 : " + ob);
        log.info("耗时 : " + (System.currentTimeMillis() - startTime) + "ms");
        return ob;
    }

}
