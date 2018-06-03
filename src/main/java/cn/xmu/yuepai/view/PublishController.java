package cn.xmu.yuepai.view;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.Review;
import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.service.PostService;
import cn.xmu.yuepai.service.ShowService;
import cn.xmu.yuepai.service.UserService;
import cn.xmu.yuepai.util.FileUtil;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/{userId}/upload", method = POST)
    public String uploadImage(@RequestParam("img") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        String filePath = "src/main/resources/static/img/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {

            // TODO: handle exception

        }
        return "/static/img/" + fileName;
    }
    /**
     * 发布摄影分享，新建username+"Image"的topic
     * @param userID
     * @param imageShare
     * @return ImageShare
     */
    @RequestMapping(value = "/{userID}/imageshare/publish", method = POST)
    public ImageShare publishImageShare(@PathVariable("userID") int userID,@RequestBody ImageShare imageShare){
        imageShare.setUserID(userID);
        System.out.println("image："+imageShare.getImage());
        System.out.println("shootTime："+imageShare.getShootTime());
        System.out.println("description："+imageShare.getDescription());
        int newID=postService.addImage(imageShare);
        System.out.println("插入数据库后ImageShareID: "+newID);
        ImageShare newImageShare=showService.getImageByImageId(newID);
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
            //创建名称为username+"Image"的topic
            destination=session.createTopic(me.getName()+"Image");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //启动连接
            connection.start();
            //发送消息
            ObjectMessage message=session.createObjectMessage();
            message.setObject(newImageShare);
            messageProducer.send(message);
            System.out.println("摄影消息"+message.toString());
            System.out.println("发送摄影图片消息的时间===》"+newImageShare.getReleaseTime());
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

    /**
     * 发布约拍，新建username+"Invitation"的topic
     * @param userID
     * @param invitation
     * @return Invitation
     */
    @RequestMapping(value = "/{userID}/invitation/publish", method = POST)
    public @ResponseBody
    Invitation publishInvitation(@PathVariable("userID") int userID, @RequestBody Invitation invitation) {
        invitation.setUserID(userID);
        int newID=postService.addInvitation(invitation);
        System.out.println("插入数据库后invitationID："+newID);
        Invitation newInvitation=showService.getInvitationByInvitationId(newID);
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
            //创建名称为username+"Invitation"的topic
            destination=session.createTopic(me.getName()+"Invitation");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //启动连接
            connection.start();
            //发送消息
            ObjectMessage message=session.createObjectMessage();
            message.setObject(newInvitation);
            messageProducer.send(message);
            System.out.println("发送约拍信息===》"+newInvitation.getContent());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        return newInvitation;
    }

    /**
     * 给某张图片点赞
     * @param userID
     * @param imageshareID
     */
    @RequestMapping(value = "/{userID}/imageshare/{imageshareID}/love", method = POST)
    public void loveImageShare(@PathVariable("userID") int userID,@PathVariable("imageshareID")int imageshareID){
        postService.addLikeImage(imageshareID);
    }

    /**
     * 给某个约拍点赞
     * @param userID
     * @param invitationID
     */
    @RequestMapping(value = "/{userID}/invitation/{invitationID}/love", method = POST)
    public void loveInvitation(@PathVariable("userID") int userID,@PathVariable("invitationID")int invitationID){
        postService.addLikeInvitation(invitationID);
    }

    /**
     * 给图片评论
     * @param userID
     * @param imageshareID
     * @param review
     */
    @RequestMapping(value = "/{userID}/imageshare/{imageshareID}/review", method = POST)
    public void reviewImageShare(@PathVariable("userID") int userID, @PathVariable("imageshareID")int imageshareID, @RequestBody Review review){
        //category=0表示图片
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos=new ParsePosition(0);
        Date nowDate=df.parse(df.format(System.currentTimeMillis()),pos);
        postService.addCommentById(0,imageshareID,userID,review.getComment(),nowDate);
    }

    /**
     * 给约拍评论
     * @param userID
     * @param invitationID
     * @param review
     */
    @RequestMapping(value = "/{userID}/invitation/{invitationID}/review", method = POST)
    public void reviewInvitation(@PathVariable("userID") int userID,@PathVariable("invitationID")int invitationID,@RequestBody Review review){
        //category=1表示约拍
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos=new ParsePosition(0);
        Date nowDate=df.parse(df.format(System.currentTimeMillis()),pos);
        postService.addCommentById(1,invitationID,userID,review.getComment(),nowDate);
    }
}
