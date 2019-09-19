package com.ats.dairydistributorapp.model;

import java.util.List;

public class GetCatItemList {

    private int catId;
    private String catEngName;
    private String catMarName;
    private String catPic;
    private int isUsed;
    List<GetAllItemsForRegOrder> allItemList;

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

    public List<GetAllItemsForRegOrder> getAllItemList() {
        return allItemList;
    }

    public void setAllItemList(List<GetAllItemsForRegOrder> allItemList) {
        this.allItemList = allItemList;
    }

    @Override
    public String toString() {
        return "GetCatItemList{" +
                "catId=" + catId +
                ", catEngName='" + catEngName + '\'' +
                ", catMarName='" + catMarName + '\'' +
                ", catPic='" + catPic + '\'' +
                ", isUsed=" + isUsed +
                ", allItemList=" + allItemList +
                '}';
    }
}
