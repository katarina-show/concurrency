package com.learn.concurrency.example.mq;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Katerina
 * @Date: 2018/8/27 1:21
 * @Description: 消息实体
 **/
@Data
public class Message {

    private Long id;

    private String msg;

    private Date sendTime;
}
