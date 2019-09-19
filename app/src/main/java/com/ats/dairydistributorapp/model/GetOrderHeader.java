package com.ats.dairydistributorapp.model;

public class GetOrderHeader {

    private int orderHeaderId;
    private int orderType;
    private int distId;
    private float orderTotal;
    private int prevPendingCrateBal;
    private float prevPendingAmt;

    public int getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderHeaderId(int orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public float getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        this.orderTotal = orderTotal;
    }

    public int getPrevPendingCrateBal() {
        return prevPendingCrateBal;
    }

    public void setPrevPendingCrateBal(int prevPendingCrateBal) {
        this.prevPendingCrateBal = prevPendingCrateBal;
    }

    public float getPrevPendingAmt() {
        return prevPendingAmt;
    }

    public void setPrevPendingAmt(float prevPendingAmt) {
        this.prevPendingAmt = prevPendingAmt;
    }

    @Override
    public String toString() {
        return "GetOrderHeader{" +
                "orderHeaderId=" + orderHeaderId +
                ", orderType=" + orderType +
                ", distId=" + distId +
                ", orderTotal=" + orderTotal +
                ", prevPendingCrateBal=" + prevPendingCrateBal +
                ", prevPendingAmt=" + prevPendingAmt +
                '}';
    }
}
