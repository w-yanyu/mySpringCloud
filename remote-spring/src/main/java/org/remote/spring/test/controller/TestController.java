package org.remote.spring.test.controller;

import org.remote.spring.test.service.Bairong;
import org.remote.spring.test.service.Pudao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    private Pudao pudao;
    private Bairong bairong;

    @Autowired
    public TestController(Pudao pudao, Bairong bairong) {
        this.pudao = pudao;
        this.bairong = bairong;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/a")
    public void a() {
        pudao.pudao001();
        pudao.pudao002();
        bairong.bairong001();
        bairong.bairong002();
    }

}
