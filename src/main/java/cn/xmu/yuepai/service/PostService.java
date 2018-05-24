package cn.xmu.yuepai.service;

import cn.xmu.yuepai.entity.ImageShare;
import cn.xmu.yuepai.entity.Invitation;


import java.util.Date;

public interface PostService {

    /**
     * 分享图片
     * @param imageShare
     */
    int addImage(ImageShare imageShare);

    /**
     * 发布约拍
     * @param invitation
     */
    int addInvitation(Invitation invitation);

    /**
     * 点赞图片
     * @param imageId
     */
    void addLikeImage(int imageId);

    /**
     * 点赞约拍
     * @param invitationId
     */
    void addLikeInvitation(int invitationId);

    /**
     * 评论图片或约拍
     * @param category
     * @param objectId
     * @param reviewerId
     * @param comment
     * @param reviewTime
     */
    int addCommentByImageId(int category, int objectId, int reviewerId, String comment, Date reviewTime);
}
