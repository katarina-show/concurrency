package com.learn.concurrency.example.mq.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:32
 * @Description: RabbitMQ 客户端
 **/
@Component
public class RabbitMQClient {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message){
        rabbitTemplate.convertAndSend(QueueConstants.TEST,message);
    }
}
