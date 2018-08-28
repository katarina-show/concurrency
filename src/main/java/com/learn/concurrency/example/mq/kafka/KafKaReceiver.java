package com.learn.concurrency.example.mq.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:12
 * @Description:
 **/
@Component
@Slf4j
public class KafKaReceiver {

    @KafkaListener(topics = {TopicConstants.TEST})
    public void receive(ConsumerRecord<?,?> record){
        log.info("record:{}",record);
    }
}
