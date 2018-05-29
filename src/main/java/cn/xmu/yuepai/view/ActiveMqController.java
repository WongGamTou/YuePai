package cn.xmu.yuepai.view;

import cn.xmu.yuepai.service.ProductService;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import javax.naming.NamingException;
import javax.xml.soap.Text;

@RestController
public class ActiveMqController {

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = "tcp://localhost:61616";

//    @Autowired
//    private Topic topic;

    @Autowired
    private ConnectionFactory connectionFactory;

//    @Autowired
//    private ProductService productService;

    @RequestMapping("/topic/{msg}")
    public void sendTopic(@PathVariable("msg") String msg) {
        Connection connection = null;//连接

        Session session;//会话 接受或者发送消息的线程

        Destination destination;//消息的目的地

        //消息生产者
        MessageProducer messageProducer;

        //实例化连接工厂(连接到ActiveMQ服务器)
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        try{
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //创建session
            session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
            //创建一个名称为zh-topic的消息队列(生产者生成的消息放在哪)
            //String d = "topic-" + msg;
            destination = session.createTopic("dwy-topic");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //启动连接
            connection.start();
            //发送消息
            sendMessage(session, messageProducer, msg);

            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }


//        System.out.println("Producer send.");
//        productService.sendMessage(this.topic, msg);
    }

    @RequestMapping("/topic/receive")
    public void receiveTopic() throws NamingException {
        Connection connection = null;//连接

        Session session;//会话 接受或者发送消息的线程

        Destination destination;//消息的目的地

        MessageConsumer messageConsumer;//消息的消费者

        Topic topic;//消息的目的地

        try {
            //for (int i=1; i<=2; i++) {
                //实例化连接工厂(连接到ActiveMQ服务器)
                connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
                //通过连接工厂获取连接
                connection = connectionFactory.createConnection();
                //connection.setClientID("t1");
                //启动连接
                connection.start();
                //创建session
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                //生产者将消息发送到zh-topic，所以消费者要到zh-topic去取
                //String to = "topic-" + i;
                //topic = session.createTopic("dwy-topic");

                destination = session.createTopic("dwy-topic");

                //创建消息消费者
                MessageConsumer consumer = session.createConsumer(destination);

                consumer.setMessageListener(message -> {
                    try {
                        System.out.println("======》收到消息："+((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });


                //设置监听器
//                consumer.setMessageListener(new MessageListener(){
//                    @Override
//                    public void onMessage(Message message) {
//                        if(message instanceof TextMessage){
//                            try{
//                                System.out.println("======》收到消息："+((TextMessage) message).getText());
//                                message.acknowledge();
//                            } catch (JMSException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//                Message message = consumer.receive();
//                while (message != null) {
//                    TextMessage txtMsg = (TextMessage) message;
//                    System.out.println("收到消息：" + txtMsg.getText());
//                    //没这句有错
//                    message = consumer.receive(5000);
//                }
//                session.commit();
//                session.close();
//                connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送消息
     *
     * @param session
     * @param messageProducer 消息生产者
     * @throws Exception
     */
    public static void sendMessage(Session session, MessageProducer messageProducer, String msg) throws Exception {
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + msg);
            System.out.println("发送消息：Activemq 发送消息" + msg);
            //通过消息生产者发出消息
            messageProducer.send(message);
    }
}