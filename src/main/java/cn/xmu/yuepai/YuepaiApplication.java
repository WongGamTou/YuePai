package cn.xmu.yuepai;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Topic;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJms
public class YuepaiApplication {

	@Bean
	public Topic topic(){
		return new ActiveMQTopic("zh-topic");
	}

	public static void main(String[] args) {
		SpringApplication.run(YuepaiApplication.class, args);
	}
}
