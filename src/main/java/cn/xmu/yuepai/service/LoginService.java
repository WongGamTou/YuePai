package cn.xmu.yuepai.service;

import cn.xmu.yuepai.entity.User;

public interface LoginService {

    /**
     * 用户登陆
     * @param name
     * @param password
     */
    int login(String name, String password);

    /**
     * 注册用户
     * @param user
     */
    int register(User user);

}
