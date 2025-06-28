package org.cloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.comm.page.PageQuery;
import com.example.common.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PaymentController
 * @Author yanyu
 * @Date 2024/5/11 13:25
 * @Version 1.0
 * @Description TODO
 **/
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Async
    @PostMapping("/buy/{userid}")
    // @RequestBody List<String> goodIds
    public void buyGood(@PathVariable String userid,
                        @RequestBody List<String> goodIds){
        boolean b = paymentService.payOrder(userid, goodIds);
        if (b){
            log.info("购买成功！！");
        }else {
            log.error("购买失败！！");

        }
    }

    @PostMapping("/getUsersByPage")
    public Page<User> getUsersByPage(@RequestBody List<String> goodIds){
        log.info("heheheheheheh");
        return null;
    }

}
