package cn.xmu.yuepai.view;


import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.service.ShowService;
import cn.xmu.yuepai.service.UserService;
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
    public List<ImageShare> getAttentionImageShare(@PathVariable("userID") int userID){
        List<User> follows = userService.getFollowersByUserId(userID);
        List<ImageShare> imageShares = new ArrayList<ImageShare>();
        for (User user : follows) {
            List<ImageShare> temp = showService.getImagesByUserId(user.getId());
            imageShares.addAll(temp);
        }
        return imageShares;
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
    public List<Invitation> getAttentionInvitation(@PathVariable("userID") int userID,
                                          @PathVariable("invitationID") int invitationID){
        List<User> follows = userService.getFollowersByUserId(userID);
        List<Invitation> invitations = new ArrayList<Invitation>();
        for (User user : follows) {
            List<Invitation> temp = showService.getInvitationsByUserId(user.getId());
            invitations.addAll(temp);
        }
        return invitations;
    }

}
