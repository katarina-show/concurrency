package com.learn.concurrency.example.mq;

import com.learn.concurrency.example.mq.kafka.KafKaSender;
import com.learn.concurrency.example.mq.rabbitmq.RabbitMQClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:36
 * @Description:
 **/
@Controller
@RequestMapping("/mq")
public class MQController {

    @Resource
    private RabbitMQClient rabbitMQClient;

    @Resource
    private KafKaSender kafKaSender;

    @RequestMapping("/send")
    public String send(@RequestParam("message") String message){
        rabbitMQClient.send(message);
        kafKaSender.send(message);
        return "success";
    }

}
