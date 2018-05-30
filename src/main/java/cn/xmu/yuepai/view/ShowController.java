package cn.xmu.yuepai.view;


import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ShowController {


    @RequestMapping(value = "/{userID}/imageshare/recommand", method = GET)
    public List<ImageShare> getRecommandImageShare(@PathVariable("userID") int userID){
        return null;
    }

    @RequestMapping(value = "/{userID}/imageshare/attention", method = GET)
    public List<ImageShare> getAttentionImageShare(@PathVariable("userID") int userID){
        return null;
    }

    @RequestMapping(value = "/{userID}/imageshare/{imageshreaID}/details", method = GET)
    public ImageShare getImageShareDatails(@PathVariable("userID") int userID,
                                    @PathVariable("imageshreaID") int imageshreaID){
        return null;
    }

    @RequestMapping(value = "/{userID}/invitation/recommand", method = GET)
    public List<Invitation> getRecommandInvitation(@PathVariable("userID") int userID,
                                    @PathVariable("invitationID") int invitationID){
        return null;
    }

    @RequestMapping(value = "/{userID}/invitation/attention", method = GET)
    public List<Invitation> getAttentionInvitation(@PathVariable("userID") int userID,
                                          @PathVariable("invitationID") int invitationID){
        return null;
    }

}
