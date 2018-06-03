package cn.xmu.yuepai.view;


import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.service.ShowService;
import cn.xmu.yuepai.service.UserService;
import cn.xmu.yuepai.view.vo.UserImageVO;
import cn.xmu.yuepai.view.vo.UserInvitationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ShowController {

    @Autowired
    ShowService showService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{userID}/imageshare/recommand", method = GET)
    public List<ImageShare> getRecommandImageShare(@PathVariable("userID") int userID){
        return null;
    }

    @RequestMapping(value = "/{userID}/imageshare/attention", method = GET)
    public List<UserImageVO> getAttentionImageShare(@PathVariable("userID") int userID){
        List<User> follows = userService.getFollowersByUserId(userID);
        List<UserImageVO> images= new ArrayList<>();
        for (User user : follows) {
            List<ImageShare> temp = showService.getImagesByUserId(user.getId());
            for(ImageShare image:temp){
                UserImageVO imageVO=new UserImageVO(image.getId(),user.getName(),image.getDescription(),image.getImage());
                images.add(imageVO);
            }
        }
        return images;
    }

    @RequestMapping(value = "/{userID}/imageshare/{imageshreaID}/details", method = GET)
    public ImageShare getImageShareDatails(@PathVariable("userID") int userID,
                                    @PathVariable("imageshreaID") int imageshreaID){
        return showService.getImageByImageId(imageshreaID);
    }

    @RequestMapping(value = "/{userID}/invitation/recommand", method = GET)
    public List<Invitation> getRecommandInvitation(@PathVariable("userID") int userID,
                                    @PathVariable("invitationID") int invitationID){
        return null;
    }

    @RequestMapping(value = "/{userID}/invitation/attention", method = GET)
    public List<UserInvitationVO> getAttentionInvitation(@PathVariable("userID") int userID){
        List<User> follows = userService.getFollowersByUserId(userID);
        List<UserInvitationVO> userInvitationVOS = new ArrayList<UserInvitationVO>();
        for (User user : follows) {
            List<Invitation> temp = showService.getInvitationsByUserId(user.getId());
            for (Invitation invitation : temp) {
                UserInvitationVO userInvitationVO = new UserInvitationVO(user.getName(), user.getUserImage(),
                        invitation.getContent(), invitation.getReleaseTime(), invitation.getLoveNumber());
                userInvitationVOS.add(userInvitationVO);
            }
        }
        return userInvitationVOS;
    }

}
