package cn.xmu.yuepai.view.vo;

import java.util.Date;

public class UserInvitationVO {
    private String userName;
    private String userImage;
    private String content;
    private Date releaseTime;
    private int loveNumber;

    public UserInvitationVO(String userName, String userImage, String content, Date releaseTime, int loveNumber) {
        this.userName = userName;
        this.userImage = userImage;
        this.content = content;
        this.releaseTime = releaseTime;
        this.loveNumber = loveNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getLoveNumber() {
        return loveNumber;
    }

    public void setLoveNumber(int loveNumber) {
        this.loveNumber = loveNumber;
    }
}
