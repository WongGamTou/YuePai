package cn.xmu.yuepai.view;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class PublishController {

    @RequestMapping(value = "/{userID}/imageshare/publish", method = POST)
    public ImageShare publishImageShare(@PathVariable("userID") int userID){
        return null;
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
