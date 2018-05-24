package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.service.PostService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

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

    }

    @Override
    public void addLikeInvitation(int invitationId) {

    }

    @Override
    public int addCommentByImageId(int category, int objectId, int reviewerId, String comment, Date reviewTime) {
        return 0;
    }
}
