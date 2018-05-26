package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.Review;
import cn.xmu.yuepai.mapper.PostMapper;
import cn.xmu.yuepai.mapper.ShowMapper;
import cn.xmu.yuepai.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    @Autowired(required = false)
    PostMapper postMapper;
    @Autowired(required = false)
    ShowMapper showMapper;

    @Override
    public int addImage(ImageShare imageShare) {
        return 0;
    }

    @Override
    public int addInvitation(Invitation invitation) {
        return 0;
    }

    @Override
    public void addLikeImage(int imageId) {
        ImageShare imageShare = showMapper.getImageByImageId(imageId);
        int num = imageShare.getLoveNumber() + 1;
        postMapper.addLikeImage(imageId, num);
    }

    @Override
    public void addLikeInvitation(int invitationId) {
        Invitation invitation = showMapper.getInvitationByInvitationId(invitationId);
        int num = invitation.getLoveNumber() + 1;
        postMapper.addLikeInvitation(invitationId, num);
    }

    @Override
    public int addCommentByImageId(int category, int objectId, int reviewerId, String comment, Date reviewTime) {
        Review review = new Review(0, category, objectId, reviewerId, reviewTime, comment);
        return postMapper.addCommentById(review);
    }
}
