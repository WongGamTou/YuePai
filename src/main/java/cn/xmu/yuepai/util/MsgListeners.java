package cn.xmu.yuepai.util;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Topic;

@Configuration
@EnableJms
public class MsgListeners {
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("zh-topic");
    }
}
