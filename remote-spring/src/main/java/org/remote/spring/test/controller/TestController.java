package org.remote.spring.test.controller;

import org.remote.spring.test.service.Bairong;
import org.remote.spring.test.service.Pudao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private Pudao pudao;

    @Autowired
    private Bairong bairong;

    @RequestMapping(method = RequestMethod.GET, value = "/a")
    public void a() {
        System.out.println("11111");
        pudao.pudao001();
        pudao.pudao002();
        bairong.bairong001();
        bairong.bairong002();
    }

}
