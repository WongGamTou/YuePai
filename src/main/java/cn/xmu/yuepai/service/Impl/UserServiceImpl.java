package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.mapper.ShowMapper;
import cn.xmu.yuepai.mapper.UserMapper;
import cn.xmu.yuepai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    ShowMapper showMapper;

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public int addFollow(int logerId, int fansId) {
        return userMapper.addFollow(logerId, fansId);
    }

    @Override
    public List<User> getFansByUserId(int userId) {
        List<Integer> ids = userMapper.getFansIdByFollowedId(userId);
        return userMapper.getFansByUserId(ids);
    }

    @Override
    public List<User> getFollowersByUserId(int userId) {
        List<Integer> ids = userMapper.getUserIdByFansId(userId);
        return userMapper.getFollowersByUserId(ids);
    }

    @Override
    public int getPostNumberByUserId(int userId) {
        List<ImageShare> imageShares = showMapper.getImagesByUserId(userId);
        List<Invitation> invitations = showMapper.getInvitationsByUserId(userId);
        return imageShares.size() + invitations.size();
    }
}
