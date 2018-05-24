package cn.xmu.yuepai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class YuepaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuepaiApplication.class, args);
	}
}
