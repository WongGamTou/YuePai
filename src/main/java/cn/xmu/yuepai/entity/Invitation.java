package cn.xmu.yuepai.entity;

import java.io.Serializable;
import java.util.Date;

public class Invitation implements Serializable{
    private int id;
    private int userID;
    private String content;
    private Date releaseTime;
    private int loveNumber;

    public Invitation(int id, int userID, String content,
                      Date releaseTime, int loveNumber) {
        this.id = id;
        this.userID = userID;
        this.content = content;
        this.releaseTime = releaseTime;
        this.loveNumber = loveNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
