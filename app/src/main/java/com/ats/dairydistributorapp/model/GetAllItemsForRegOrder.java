package com.ats.dairydistributorapp.model;

public class GetAllItemsForRegOrder {

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
    private String uomName;
    private float itemCgst;
    private float itemSgst;
    private float itemIgst;
    private int orderQty;
    private int orderDetailId;
    private int newQty;
    private int isQtyChanged;

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

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public float getItemCgst() {
        return itemCgst;
    }

    public void setItemCgst(float itemCgst) {
        this.itemCgst = itemCgst;
    }

    public float getItemSgst() {
        return itemSgst;
    }

    public void setItemSgst(float itemSgst) {
        this.itemSgst = itemSgst;
    }

    public float getItemIgst() {
        return itemIgst;
    }

    public void setItemIgst(float itemIgst) {
        this.itemIgst = itemIgst;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getNewQty() {
        return newQty;
    }

    public void setNewQty(int newQty) {
        this.newQty = newQty;
    }

    public int getIsQtyChanged() {
        return isQtyChanged;
    }

    public void setIsQtyChanged(int isQtyChanged) {
        this.isQtyChanged = isQtyChanged;
    }

    @Override
    public String toString() {
        return "GetAllItemsForRegOrder{" +
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
                ", uomName='" + uomName + '\'' +
                ", itemCgst=" + itemCgst +
                ", itemSgst=" + itemSgst +
                ", itemIgst=" + itemIgst +
                ", orderQty=" + orderQty +
                ", orderDetailId=" + orderDetailId +
                ", newQty=" + newQty +
                ", isQtyChanged=" + isQtyChanged +
                '}';
    }
}
