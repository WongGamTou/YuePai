package cn.xmu.yuepai.entity;

import java.util.Map;

public class User {
    private int id;
    private String phone;
    private String name;
    private String password;
    private String userImage;

    public User(int id, String phone, String name,
                String password, String userImage) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.password = password;
        this.userImage = userImage;
    }

    public User(Map<String,Object> jsonUser) {
        this.id = 0;
        this.phone =(String)(jsonUser.get("phone"));
        this.name = (String)(jsonUser.get("name"));
        this.password = (String)(jsonUser.get("password"));
        this.userImage = (String)(jsonUser.get("userImage"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
