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
    @RequestMapping(value = "/{userID}/imageshare/{imageshreaID}/details", method = GET)
    public ImageShare getImageShare(@PathVariable("userID") int userID,
                                    @PathVariable("imageshreaID") int imageshreaID){
        return null;
    }

    @RequestMapping(value = "/{userID}/imageshare/{invitationID}/details", method = GET)
    public Invitation getInvitation(@PathVariable("userID") int userID,
                                    @PathVariable("invitationID") int invitationID){
        return null;
    }

    @RequestMapping(value = "{userID}/imageshare/attention",method = GET)
    public List<ImageShare> getAttentionImage() {
        return null;
    }

}
