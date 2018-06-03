package cn.xmu.yuepai.view;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.service.LoginService;
import cn.xmu.yuepai.service.ShowService;
import cn.xmu.yuepai.service.UserService;
import cn.xmu.yuepai.view.vo.UserInvitationVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserController {

    //默认连接用户名 密码 地址
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = "tcp://localhost:61616";

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    ShowService showService;

    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 登录
     */
    @RequestMapping(value = "/login", method = POST)
    public ObjectNode login(HttpServletRequest httpServletRequest) throws IOException, JMSException {
        BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        Map<String , Object> user_temp = new ObjectMapper().readValue(wholeStr, Map.class);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode returnMessage = mapper.createObjectNode();
        int userId = loginService.login((String) user_temp.get("userName"), (String) user_temp.get("password"));
        if (userId >= 0) {
            returnMessage.put("userId", userId);
            System.out.println("UserId:"+userId);
            User user = userService.getUserByName((String)user_temp.get("userName"));
            List<User> follows = userService.getFollowersByUserId(user.getId());
            for (int i = 0; i < follows.size(); i++) {
                connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
                Connection connection = connectionFactory.createConnection();       //通过连接工厂获取连接
                connection.setClientID((String)user_temp.get("userName") + i);
                System.out.println("Username:" + (String) user_temp.get("userName"));
                connection.start();        //启动连接

                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//创建session

                Destination topicImage = session.createTopic(follows.get(i).getName() + "Image");
                System.out.println("Listen ImageTopicName:" + follows.get(i).getName() + "Image");
                Destination topicInvitation = session.createTopic(follows.get(i).getName() + "Invitation");
                System.out.println("Listen InvitationTopicName:" + follows.get(i).getName() + "Invitation");

                //创建消息消费者
                MessageConsumer consumerImage = session.createConsumer(topicImage, (String) user_temp.get("userName") + "Image" + i);
                MessageConsumer consumerInvitation = session.createConsumer(topicInvitation, (String) user_temp.get("userName") + "Invitation" + i);

                consumerImage.setMessageListener(message -> {
                    try {
                        System.out.println("======》收到消息："+((ObjectMessage) message).getObject().toString());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });

                consumerInvitation.setMessageListener(message -> {
                    try {
                        System.out.println("======》收到消息："+((ObjectMessage) message).getObject().toString());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                });

            }
        }
        return returnMessage;
    }


    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = POST)
    public int register(HttpServletRequest httpServletRequest) throws IOException {
        BufferedReader br = httpServletRequest.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        Map<String , Object> user_temp = new ObjectMapper().readValue(wholeStr, Map.class);
        User user = new User(user_temp);
        return loginService.register(user);
    }

    /**
     * 根据用户名搜索
     * @param userID
     * @param userName
     * @return
     */
    @RequestMapping(value = "/{userID}/search/{userName}", method = GET)
    public User getUserByName(@PathVariable("userID") int userID,
                              @PathVariable("userName") String userName) {
        return userService.getUserByName(userName);
    }

    /**
     * 添加关注
     * @param userID
     * @param otherUserID
     * @return
     */
    @RequestMapping(value = "/{userID}/adduser/{otherUserID}", method = POST)
    public void addAttention(@PathVariable("userID") int userID,
                             @PathVariable("otherUserID") int otherUserID) {
        userService.addFollow(otherUserID, userID);
    }

    /**
     * 获取其他用户分享的图片
     * @param userID
     * @param otherUserID
     * @return
     */
    @RequestMapping(value = "/{userID}/otheruser/{otherUserID}/imageshare", method = GET)
    public List<ImageShare> getOtherUserImage(@PathVariable("userID") int userID,
                                              @PathVariable("otherUserID") int otherUserID) {
        return showService.getImagesByUserId(otherUserID);
    }

    /**
     * 获取其他用户的约拍
     * @param userID
     * @param otherUserID
     * @return
     */
    @RequestMapping(value = "/{userID}/otheruser/{otherUserID}/invitation", method = GET)
    public List<Invitation> getOtherUserInvitation(@PathVariable("userID") int userID,
                                                   @PathVariable("otherUserID") int otherUserID) {
        return showService.getInvitationsByUserId(otherUserID);
    }

    /**
     * 获取我的照片
     * @param userID
     * @return
     */
    @RequestMapping(value = "/{userID}/homepage/imageshare", method = GET)
    public List<ImageShare> getMyImageShare(@PathVariable("userID") int userID) {
        return showService.getImagesByUserId(userID);
    }

    /**
     * 获取我的主页的约拍
     * @param userID
     * @return
     */
    @RequestMapping(value = "/{userID}/homepage/invitation", method = GET)
    public List<UserInvitationVO> getMyInvitation(@PathVariable("userID") int userID) {
        List<Invitation> tmp=showService.getInvitationsByUserId(userID);
        List<UserInvitationVO> invitations=new ArrayList<>();
        User user=userService.getUserById(userID);
        for(Invitation i:tmp){
            UserInvitationVO j=new UserInvitationVO(user.getName(),user.getUserImage(),i.getContent(),i.getReleaseTime(),i.getLoveNumber());
            invitations.add(j);
        }
        return invitations;
    }

    /**
     * 获取我的通知
     * @param userID
     */
    @RequestMapping(value = "/{userID}/homepage/notice", method = GET)
    public void getNotice(@PathVariable("userID") int userID) {

    }

    @RequestMapping(value="/{userID}/homepage",method=GET)
    public Map getUserInfo(@PathVariable("userID") int userID){
        User me=userService.getUserById(userID);
        String username=me.getName();
        String userImage=me.getUserImage();
        int numOfActivity=userService.getPostNumberByUserId(userID);
        int numOfFans=userService.getFansByUserId(userID).size();
        int numOfBlogger=userService.getFollowersByUserId(userID).size();
        Map<String,String> map=new HashMap<String, String>();
        map.put("username",username);
        map.put("userImage",userImage);
        map.put("numOfActivity",""+numOfActivity);
        map.put("numOfFans",""+numOfFans);
        map.put("numOfBlogger",""+numOfBlogger);
        return map;
    }
}
