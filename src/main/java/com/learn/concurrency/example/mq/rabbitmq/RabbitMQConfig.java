package com.learn.concurrency.example.mq.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:28
 * @Description: RabbitMQ 配置类
 **/
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue(){
        return new Queue(QueueConstants.TEST);
    }
}
