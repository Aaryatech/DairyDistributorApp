package com.ats.dairydistributorapp.model;

public class GetOrderDetail {

    private int orderDetailId;
    private int orderHeaderId;
    private int itemId;
    private float itemRate;
    private int orderQty;
    private int hubQty;
    private int msQty;
    private float itemTotal;
    private int deliverQty;
    private float itemCgst;
    private float itemSgst;
    private float itemIgst;
    private float itemBasicValue;
    private String itemEngName;
    private String itemMarName;
    private String itemEngDesc;
    private String itemMarDesc;
    private String uomName;

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderHeaderId(int orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getHubQty() {
        return hubQty;
    }

    public void setHubQty(int hubQty) {
        this.hubQty = hubQty;
    }

    public int getMsQty() {
        return msQty;
    }

    public void setMsQty(int msQty) {
        this.msQty = msQty;
    }

    public float getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(float itemTotal) {
        this.itemTotal = itemTotal;
    }

    public int getDeliverQty() {
        return deliverQty;
    }

    public void setDeliverQty(int deliverQty) {
        this.deliverQty = deliverQty;
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

    public float getItemBasicValue() {
        return itemBasicValue;
    }

    public void setItemBasicValue(float itemBasicValue) {
        this.itemBasicValue = itemBasicValue;
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

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    @Override
    public String toString() {
        return "GetOrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderHeaderId=" + orderHeaderId +
                ", itemId=" + itemId +
                ", itemRate=" + itemRate +
                ", orderQty=" + orderQty +
                ", hubQty=" + hubQty +
                ", msQty=" + msQty +
                ", itemTotal=" + itemTotal +
                ", deliverQty=" + deliverQty +
                ", itemCgst=" + itemCgst +
                ", itemSgst=" + itemSgst +
                ", itemIgst=" + itemIgst +
                ", itemBasicValue=" + itemBasicValue +
                ", itemEngName='" + itemEngName + '\'' +
                ", itemMarName='" + itemMarName + '\'' +
                ", itemEngDesc='" + itemEngDesc + '\'' +
                ", itemMarDesc='" + itemMarDesc + '\'' +
                ", uomName='" + uomName + '\'' +
                '}';
    }
}
