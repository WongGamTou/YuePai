package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.mapper.ShowMapper;
import cn.xmu.yuepai.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired(required = false)
    ShowMapper showMapper;

    @Override
    public List<ImageShare> getImagesByUserId(int userID) {
        return showMapper.getImagesByUserId(userID);
    }

    @Override
    public ImageShare getImageByImageId(int imageId) {
        return showMapper.getImageByImageId(imageId);
    }

    @Override
    public List<Invitation> getInvitationsByUserId(int userId) {
        return showMapper.getInvitationsByUserId(userId);
    }

    @Override
    public Invitation getInvitationByInvitationId(int invitationId) {
        return showMapper.getInvitationByInvitationId(invitationId);
    }
}
