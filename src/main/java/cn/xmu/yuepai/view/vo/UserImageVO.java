package cn.xmu.yuepai.view.vo;


public class UserImageVO {
    private int imageId;
    private String userName;
    private String description;
    private String image;


    public UserImageVO(int imageId,String userName,String description,String image){
        this.imageId=imageId;
        this.userName=userName;
        this.description=description;
        this.image=image;
    }
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
