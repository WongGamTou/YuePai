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
    public int login(String name, String password) {
        User user = userMapper.getUserByName(name);
        if (user == null) {
            return 0;
        }
        else {
            if (!user.getPassword().equals(password)) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int register(User user) {
        return userMapper.insertUser(user);
    }
}
