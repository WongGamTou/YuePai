package cn.xmu.yuepai.view;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.service.PostService;
import cn.xmu.yuepai.service.ShowService;
import cn.xmu.yuepai.service.UserService;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import javax.naming.NamingException;
import java.awt.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class PublishController {

    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShowService showService;

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = "tcp://localhost:61616";

    @Autowired
    private ConnectionFactory connectionFactory;

    @RequestMapping(value = "/{userID}/imageshare/publish", method = POST)
    public ImageShare publishImageShare(@PathVariable("userID") int userID, @RequestBody ImageShare imageShare){
        imageShare.setUserID(userID);
        postService.addImage(imageShare);
        System.out.println("新增的ImageShare-ID"+imageShare.getId());
        ImageShare newImageShare=showService.getImageByImageId(imageShare.getId());
        User me=userService.getUserById(userID);

        Connection connection=null;//连接
        Session session;//会话
        Destination destination;//消息目的地
        MessageProducer messageProducer;//消息生产者

        //实例化连接工厂（连接到ActiveMQ服务器）
        connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEURL);
        try{
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //创建session
            session = connection.createSession(Boolean.TRUE, Session.CLIENT_ACKNOWLEDGE);
            //创建名称为username的topic
            destination=session.createTopic(me.getName());
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //启动连接
            connection.start();
            //发送消息
            ObjectMessage message=session.createObjectMessage();
            message.setObject(newImageShare);
            messageProducer.send(message);

            System.out.println("发送消息===》"+newImageShare.getReleaseTime());
            session.commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        return newImageShare;
    }

    @RequestMapping(value = "/{userID}/invitation/publish", method = POST)
    public Invitation publishInvitation(@PathVariable("userID") int userID){
        return null;
    }

    @RequestMapping(value = "/{userID}/imageshare/{imageshareID}/love", method = POST)
    public void loveImageShare(@PathVariable("userID") int userID,@PathVariable("imageshareID")int imageshareID){ }

    @RequestMapping(value = "/{userID}/invitation/{invitationID}/love", method = POST)
    public void loveInvitation(@PathVariable("userID") int userID,@PathVariable("invitationID")int invitationID){ }

    @RequestMapping(value = "/{userID}/imageshare/{imageshareID}/review", method = POST)
    public void reviewImageShare(@PathVariable("userID") int userID,@PathVariable("imageshareID")int imageshareID){ }

    @RequestMapping(value = "/{userID}/invitation/{invitationID}/review", method = POST)
    public void reviewInvitation(@PathVariable("userID") int userID,@PathVariable("invitationID")int invitationID){ }
}
