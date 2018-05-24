package cn.xmu.yuepai.service.Impl;

import cn.xmu.yuepai.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public int login(int userID, String password) {
        return 0;
    }

    @Override
    public int register(int id, String phone, String name, String password, String userImage) {
        return 0;
    }
}
