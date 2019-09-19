package com.ats.dairydistributorapp.model;

import java.util.List;

public class CategoryItems {

    private int catId;
    private String catEngName;
    private String catMarName;
    private String catPic;
    private int isUsed;
    List<GetItem> getItemList;

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

    public List<GetItem> getGetItemList() {
        return getItemList;
    }

    public void setGetItemList(List<GetItem> getItemList) {
        this.getItemList = getItemList;
    }

    @Override
    public String toString() {
        return "CategoryItems{" +
                "catId=" + catId +
                ", catEngName='" + catEngName + '\'' +
                ", catMarName='" + catMarName + '\'' +
                ", catPic='" + catPic + '\'' +
                ", isUsed=" + isUsed +
                ", getItemList=" + getItemList +
                '}';
    }
}
