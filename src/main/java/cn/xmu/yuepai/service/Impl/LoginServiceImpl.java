package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.entity.User;
import cn.xmu.yuepai.mapper.UserMapper;
import cn.xmu.yuepai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public int login(String userName, String password) {
        User user = userMapper.getUserByName(userName);
        if (user == null) {
            return -1;
        }
        else {
            if (!user.getPassword().equals(password)) {
                return user.getId();
            }
        }
        return user.getId();
    }

    @Override
    public int register(User user) {

        if (userMapper.insertUser(user) > 0)
            return user.getId();
        else
            return -1;
    }
}
