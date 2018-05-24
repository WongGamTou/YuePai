package cn.xmu.yuepai.entity;

public class Relation {
    private int id;
    private int blogerID;
    private int fansID;

    public Relation(int id, int blogerID, int fansID) {
        this.id = id;
        this.blogerID = blogerID;
        this.fansID = fansID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogerID() {
        return blogerID;
    }

    public void setBlogerID(int blogerID) {
        this.blogerID = blogerID;
    }

    public int getFansID() {
        return fansID;
    }

    public void setFansID(int fansID) {
        this.fansID = fansID;
    }
}
