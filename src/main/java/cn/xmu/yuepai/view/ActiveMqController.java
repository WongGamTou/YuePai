package cn.xmu.yuepai.view;

import cn.xmu.yuepai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

@RestController
public class ActiveMqController {
    @Autowired
    private Topic topic;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ProductService productService;

    @RequestMapping("/topic/{msg}")
    public void sendTopic(@PathVariable("msg") String msg){
        System.out.println("Producer send.");
        productService.sendMessage(this.topic, msg);
    }
}
