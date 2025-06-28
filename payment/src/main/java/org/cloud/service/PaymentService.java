package org.cloud.service;

import java.util.List;

/**
 * @Author yanyu
 * @Date 2024/5/11 13:30
 * @Version 1.0
 * @Description TODO
 **/
public interface PaymentService {

    boolean payOrder(String userid, List<String> goodIds);
}
