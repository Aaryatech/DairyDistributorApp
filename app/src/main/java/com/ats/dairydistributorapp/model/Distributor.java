package com.ats.dairydistributorapp.model;

public class Distributor {

    private int distId;
    private String distEngName;
    private String distMarName;
    private String distAddMar;
    private String distAddEng;
    private String distContactNo;
    private String distPwd;
    private int hubId;
    private int routeId;
    private String token;
    private String routeDistSeqNo;
    private int distCratesPending;
    private float distAmtPending;
    private int distCratesLimit;
    private float distAmtLimit;
    private String distLocation;
    private int isBlock;
    private int isUsed;

    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public String getDistEngName() {
        return distEngName;
    }

    public void setDistEngName(String distEngName) {
        this.distEngName = distEngName;
    }

    public String getDistMarName() {
        return distMarName;
    }

    public void setDistMarName(String distMarName) {
        this.distMarName = distMarName;
    }

    public String getDistAddMar() {
        return distAddMar;
    }

    public void setDistAddMar(String distAddMar) {
        this.distAddMar = distAddMar;
    }

    public String getDistAddEng() {
        return distAddEng;
    }

    public void setDistAddEng(String distAddEng) {
        this.distAddEng = distAddEng;
    }

    public String getDistContactNo() {
        return distContactNo;
    }

    public void setDistContactNo(String distContactNo) {
        this.distContactNo = distContactNo;
    }

    public String getDistPwd() {
        return distPwd;
    }

    public void setDistPwd(String distPwd) {
        this.distPwd = distPwd;
    }

    public int getHubId() {
        return hubId;
    }

    public void setHubId(int hubId) {
        this.hubId = hubId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRouteDistSeqNo() {
        return routeDistSeqNo;
    }

    public void setRouteDistSeqNo(String routeDistSeqNo) {
        this.routeDistSeqNo = routeDistSeqNo;
    }

    public int getDistCratesPending() {
        return distCratesPending;
    }

    public void setDistCratesPending(int distCratesPending) {
        this.distCratesPending = distCratesPending;
    }

    public float getDistAmtPending() {
        return distAmtPending;
    }

    public void setDistAmtPending(float distAmtPending) {
        this.distAmtPending = distAmtPending;
    }

    public int getDistCratesLimit() {
        return distCratesLimit;
    }

    public void setDistCratesLimit(int distCratesLimit) {
        this.distCratesLimit = distCratesLimit;
    }

    public float getDistAmtLimit() {
        return distAmtLimit;
    }

    public void setDistAmtLimit(float distAmtLimit) {
        this.distAmtLimit = distAmtLimit;
    }

    public String getDistLocation() {
        return distLocation;
    }

    public void setDistLocation(String distLocation) {
        this.distLocation = distLocation;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "distId=" + distId +
                ", distEngName='" + distEngName + '\'' +
                ", distMarName='" + distMarName + '\'' +
                ", distAddMar='" + distAddMar + '\'' +
                ", distAddEng='" + distAddEng + '\'' +
                ", distContactNo='" + distContactNo + '\'' +
                ", distPwd='" + distPwd + '\'' +
                ", hubId=" + hubId +
                ", routeId=" + routeId +
                ", token='" + token + '\'' +
                ", routeDistSeqNo='" + routeDistSeqNo + '\'' +
                ", distCratesPending=" + distCratesPending +
                ", distAmtPending=" + distAmtPending +
                ", distCratesLimit=" + distCratesLimit +
                ", distAmtLimit=" + distAmtLimit +
                ", distLocation='" + distLocation + '\'' +
                ", isBlock=" + isBlock +
                ", isUsed=" + isUsed +
                '}';
    }
}
