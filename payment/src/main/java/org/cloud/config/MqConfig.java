package org.cloud.config;

import com.example.common.mq.order.Producer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @ClassName MqConfig
 * @Author yanyu
 * @Date 2024/5/12 21:32
 * @Version 1.0
 * @Description TODO
 **/

@Import(Producer.class)
@Configuration
public class MqConfig {
}
