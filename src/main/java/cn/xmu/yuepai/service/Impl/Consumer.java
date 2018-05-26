package cn.xmu.yuepai.service.Impl;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "zh-topic")
    public void receiveQueue(String text) {
        System.out.println("Consumer receive:"+text);
    }
}