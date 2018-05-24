package cn.xmu.yuepai.service;

public interface LoginService {

    /**
     * 用户登陆
     * @param userID
     * @param password
     */
    int login(int userID, String password);

    /**
     * 注册用户
     * @param id
     * @param phone
     * @param name
     * @param password
     * @param userImage
     */
    int register(int id, String phone, String name, String password, String userImage);

}
