package com.learn.concurrency.example.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:35
 * @Description: RabbitMQ 服务端
 **/
@Slf4j
@Component
public class RabbitMQServer {

    @RabbitListener(queues = QueueConstants.TEST)
    private void receive(String message){
        log.info("{}",message);
    }
}
