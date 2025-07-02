package com.example.remote.Controller;

import com.example.common.entity.User;
import com.example.remote.service.Pudao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@RequestMapping("test")
public class TestController {

   @Autowired
    private Pudao pudao;

    @RequestMapping(method = RequestMethod.POST, value = "/query")
    public void query(@RequestBody Map<String,Object> map) {
        pudao.taxInformation(map);
        System.out.println("queryqueryqueryqueryqueryqueryqueryqueryqueryquery");
    }
}
