package com.ats.dairydistributorapp.model;

public class Category {

    private int catId;
    private String catEngName;
    private String catMarName;
    private String catPic;
    private int isUsed;

    public Category(int catId, String catEngName, String catMarName, String catPic, int isUsed) {
        this.catId = catId;
        this.catEngName = catEngName;
        this.catMarName = catMarName;
        this.catPic = catPic;
        this.isUsed = isUsed;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatEngName() {
        return catEngName;
    }

    public void setCatEngName(String catEngName) {
        this.catEngName = catEngName;
    }

    public String getCatMarName() {
        return catMarName;
    }

    public void setCatMarName(String catMarName) {
        this.catMarName = catMarName;
    }

    public String getCatPic() {
        return catPic;
    }

    public void setCatPic(String catPic) {
        this.catPic = catPic;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catId=" + catId +
                ", catEngName='" + catEngName + '\'' +
                ", catMarName='" + catMarName + '\'' +
                ", catPic='" + catPic + '\'' +
                ", isUsed=" + isUsed +
                '}';
    }
}
