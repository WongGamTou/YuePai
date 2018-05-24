package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.service.ShowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @Override
    public List<ImageShare> getImagesByUserId(int userID) {
        return null;
    }

    @Override
    public ImageShare getImageByImageId(int imageId) {
        return null;
    }

    @Override
    public List<Invitation> getInvitationsByUserId(int userId) {
        return null;
    }

    @Override
    public Invitation getInvitationByInvitationId(int invitationId) {
        return null;
    }
}
