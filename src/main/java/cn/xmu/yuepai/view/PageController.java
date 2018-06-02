package cn.xmu.yuepai.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "common/login";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register")
    public String register() {
        return "common/login";
    }

    /**
     * 个人主页
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/profile")
    public String getMyProfile(@PathVariable("userId") int userId) {
        return "profile/profile";
    }

    /**
     * 发布约拍、分享
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/publish")
    public String publish(@PathVariable("userId") int userId) {
        return "photograph/publish";
    }

    /**
     * 查看分享的图片
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/share")
    public String getShare(@PathVariable("userId") int userId) {
        return "photograph/share";
    }

    /**
     * 查看约拍
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}/photo")
    public String getPhoto(@PathVariable("userId") int userId) {
        return "photograph/photo";
    }

    /**
     * 查看他人主页
     * @param userId
     * @param otherUserId
     * @return
     */
    @RequestMapping(value = "/{userId}/{otherUserId}")
    public String getUserProfile(@PathVariable("userId") int userId, @PathVariable("otherUserId") int otherUserId) {
        return "profile/profileUsers";
    }

}
