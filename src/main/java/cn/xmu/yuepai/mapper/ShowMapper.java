package cn.xmu.yuepai.mapper;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShowMapper {

    /**
     * 获取用户分享的图片
     * @param userID
     * @return
     */
    List<ImageShare> getImagesByUserId(int userID);

    /**
     * 获取单张图片信息
     * @param imageId
     * @return
     */
    ImageShare getImageByImageId(int imageId);

    /**
     * 获取用户所有约拍信息
     * @param userId
     * @return
     */
    List<Invitation> getInvitationsByUserId(int userId);

    /**
     * 获取该条约拍信息
     * @param invitationId
     * @return
     */
    Invitation getInvitationByInvitationId(int invitationId);
}
