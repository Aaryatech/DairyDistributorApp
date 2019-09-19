package com.ats.dairydistributorapp.model;

public class Route {

    private int routeId;
    private int routeSeqNo;
    private int hubId;
    private String routeEngName;
    private String routeMarName;
    private int isUsed;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getRouteSeqNo() {
        return routeSeqNo;
    }

    public void setRouteSeqNo(int routeSeqNo) {
        this.routeSeqNo = routeSeqNo;
    }

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public String getRouteEngName() {
        return routeEngName;
    }

    public void setRouteEngName(String routeEngName) {
        this.routeEngName = routeEngName;
    }

    public String getRouteMarName() {
        return routeMarName;
    }

    public void setRouteMarName(String routeMarName) {
        this.routeMarName = routeMarName;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", routeSeqNo=" + routeSeqNo +
                ", hubId=" + hubId +
                ", routeEngName='" + routeEngName + '\'' +
                ", routeMarName='" + routeMarName + '\'' +
                ", isUsed=" + isUsed +
                '}';
    }
}
