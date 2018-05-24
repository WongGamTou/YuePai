package cn.xmu.yuepai.mapper;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;
import cn.xmu.yuepai.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface PostMapper {
    /**
     * 分享图片
     * @param imageShare
     */
    int addImage(ImageShare imageShare);

    /**
     * 发布约拍
     * @param Invitation
     */
    int addInvitation(Invitation Invitation);

    /**
     * 点赞图片
     * @param number
     */
    void addLikeImage(@Param("imageId") int imageId, @Param("number") int number);

    /**
     * 点赞约拍
     * @param number
     */
    void addLikeInvitation(@Param("invitationId") int invitationId, @Param("number") int number);

    /**
     * 评论图片或约拍
     * @param review
     */
    int addCommentById(Review review);
}
