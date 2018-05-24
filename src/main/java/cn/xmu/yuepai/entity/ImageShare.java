package cn.xmu.yuepai.entity;

import java.util.Date;

public class ImageShare {
    private int id;
    private int userID;
    private String image;
    private String category;
    private Date shootTime;
    private String cameraModel;
    private String cameraParam;
    private String description;
    private Date releaseTime;
    private int loveNumber;

    public ImageShare(int id, int userID, String image, String category,
                      Date shootTime, String cameraModel, String cameraParam,
                      String description, Date releaseTime, int loveNumber) {
        this.id = id;
        this.userID = userID;
        this.image = image;
        this.category = category;
        this.shootTime = shootTime;
        this.cameraModel = cameraModel;
        this.cameraParam = cameraParam;
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getShootTime() {
        return shootTime;
    }

    public void setShootTime(Date shootTime) {
        this.shootTime = shootTime;
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public void setCameraModel(String cameraModel) {
        this.cameraModel = cameraModel;
    }

    public String getCameraParam() {
        return cameraParam;
    }

    public void setCameraParam(String cameraParam) {
        this.cameraParam = cameraParam;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
