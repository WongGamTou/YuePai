package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    @Override
    public int addFollow(int logerId, int fansId) {
        return 0;
    }

    @Override
    public List<User> getFansByUserId(int userId) {
        return null;
    }

    @Override
    public List<User> getFollowersByUserId(int userId) {
        return null;
    }

    @Override
    public int getPostNumberByUserId(int userId) {
        return 0;
    }
}
