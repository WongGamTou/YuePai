package cn.xmu.yuepai.entity;

import java.util.Date;

public class Review {
    private int id;
    private int category;
    private int objectID;
    private int reviewerID;
    private String comment;
    private Date reviewTime;

    public Review(){}
    public Review(int id, int category, int objectID,
                  int reviewerID,String comment,Date reviewTime) {
        this.id = id;
        this.category = category;
        this.objectID = objectID;
        this.reviewerID = reviewerID;
        this.reviewTime = reviewTime;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public int getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(int reviewerID) {
        this.reviewerID = reviewerID;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment =comment;
    }
}
