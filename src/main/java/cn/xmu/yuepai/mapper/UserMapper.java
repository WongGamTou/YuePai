package cn.xmu.yuepai.mapper;

import cn.xmu.yuepai.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 注册用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户名（电话）查找用户（登陆时用）
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 获取用户
     * @param userId
     * @return
     */
    User getUserById(int userId);

    /**
     * 根据用户名获取用户
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 添加关注
     * @param blogerId
     * @param fansId
     */
    int addFollow(@Param("blogerId") int blogerId, @Param("fansId") int fansId);

    /**
     * 根据粉丝获取关注的人的ID
     * @param userId
     * @return
     */
    List<Integer> getUserIdByFansId(int userId);

    /**
     * 根据被关注人获取粉丝ID
     * @param userId
     * @return
     */
    List<Integer> getFansIdByFollowedId(int userId);

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
}