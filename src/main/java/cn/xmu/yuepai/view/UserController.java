package cn.xmu.yuepai.view;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class UserController {

    @RequestMapping(value = "/login", method = POST)
    public void login() {

    }

    @RequestMapping(value = "/register", method = POST)
    public void register() {

    }

    @RequestMapping(value = "/{userID}/search/{userName}", method = GET)
    public User getUserByName(@PathVariable("userID") int userID,
                              @PathVariable("userName") String userName) {
        return null;
    }

    /**
     * 添加关注
     * @param userID
     * @param otherUserID
     * @return
     */
    @RequestMapping(value = "/{userID}/adduser/{otheruserID}", method = POST)
    public void addAttention(@PathVariable("userID") int userID,
                             @PathVariable("otherUserID") int otherUserID) {

    }
    @RequestMapping(value = "/{userID}/otheruser/{otherUserID}/imageshare", method = GET)
    public List<ImageShare> getOtherUserImage(@PathVariable("userID") int userID,
                                              @PathVariable("otherUserID") int otherUserID) {
        return null;
    }

    @RequestMapping(value = "/{userID}/otheruser/{otherUserID}/invitation", method = GET)
    public List<Invitation> getOtherUserInvitation(@PathVariable("userID") int userID,
                                              @PathVariable("otherUserID") int otherUserID) {
        return null;
    }

    @RequestMapping(value = "/{userID}/homepage/imageshare", method = GET)
    public List<ImageShare> getMyImageShare(@PathVariable("userID") int userID) {
        return null;
    }

    @RequestMapping(value = "/{userID}/homepage/imageshare", method = GET)
    public List<Invitation> getMyInvitation(@PathVariable("userID") int userID) {
        return null;
    }

    @RequestMapping(value = "/{userID}/homepage/notice", method = GET)
    public void getNotice(@PathVariable("userID") int userID) {

    }
}
