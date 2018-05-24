package cn.xmu.yuepai.service;

import cn.xmu.yuepai.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 根据用户名获取用户信息
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 添加关注信息
     * @param logerId
     * @param fansId
     */
    int addFollow(int logerId, int fansId);

    /**
     * 获取用户的粉丝
     * @param userId
     * @return
     */
    List<User> getFansByUserId(int userId);

    /**
     * 获取用户的关注
     * @param userId
     * @return
     */
    List<User> getFollowersByUserId(int userId);

    /**
     * 获取动态数量
     * @param userId
     * @return
     */
    int getPostNumberByUserId(int userId);
}
