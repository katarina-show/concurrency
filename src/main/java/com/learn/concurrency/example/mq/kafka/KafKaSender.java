package com.learn.concurrency.example.mq.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learn.concurrency.example.mq.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:12
 * @Description:
 **/
@Slf4j
public class KafKaSender {

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void send(String msg){
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("send message:{}",message);
        //指定Topic和json字符串
        kafkaTemplate.send(TopicConstants.TEST,gson.toJson(message));
    }

}
