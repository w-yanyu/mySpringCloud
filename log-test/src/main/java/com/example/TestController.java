package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Author yanyu
 * @Date 2025/8/11 15:18
 * @Version 1.0
 * @Description TODO
 **/
@RestController("TEST")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/hello")
    public String hello() {
        logger.info("Hello endpoint hit");
        return "Hello World";
    }
}
