package com.ats.dairydistributorapp.model;

public class GetItem {

    private int itemId;
    private int itemHsnId;
    private int catId;
    private String itemPic;
    private String itemEngName;
    private String itemMarName;
    private String itemEngDesc;
    private String itemMarDesc;
    private int itemUomId;
    private int itemWt;
    private float itemRate;
    private float itemMrp;
    private int isUsed;
    private int orderQty;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemHsnId() {
        return itemHsnId;
    }

    public void setItemHsnId(int itemHsnId) {
        this.itemHsnId = itemHsnId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemEngName() {
        return itemEngName;
    }

    public void setItemEngName(String itemEngName) {
        this.itemEngName = itemEngName;
    }

    public String getItemMarName() {
        return itemMarName;
    }

    public void setItemMarName(String itemMarName) {
        this.itemMarName = itemMarName;
    }

    public String getItemEngDesc() {
        return itemEngDesc;
    }

    public void setItemEngDesc(String itemEngDesc) {
        this.itemEngDesc = itemEngDesc;
    }

    public String getItemMarDesc() {
        return itemMarDesc;
    }

    public void setItemMarDesc(String itemMarDesc) {
        this.itemMarDesc = itemMarDesc;
    }

    public int getItemUomId() {
        return itemUomId;
    }

    public void setItemUomId(int itemUomId) {
        this.itemUomId = itemUomId;
    }

    public int getItemWt() {
        return itemWt;
    }

    public void setItemWt(int itemWt) {
        this.itemWt = itemWt;
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public float getItemMrp() {
        return itemMrp;
    }

    public void setItemMrp(float itemMrp) {
        this.itemMrp = itemMrp;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "GetItem{" +
                "itemId=" + itemId +
                ", itemHsnId=" + itemHsnId +
                ", catId=" + catId +
                ", itemPic='" + itemPic + '\'' +
                ", itemEngName='" + itemEngName + '\'' +
                ", itemMarName='" + itemMarName + '\'' +
                ", itemEngDesc='" + itemEngDesc + '\'' +
                ", itemMarDesc='" + itemMarDesc + '\'' +
                ", itemUomId=" + itemUomId +
                ", itemWt=" + itemWt +
                ", itemRate=" + itemRate +
                ", itemMrp=" + itemMrp +
                ", isUsed=" + isUsed +
                ", orderQty=" + orderQty +
                '}';
    }
}
