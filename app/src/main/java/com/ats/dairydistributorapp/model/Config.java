package com.ats.dairydistributorapp.model;

public class Config {

    private int configId;
    private String configEngName;
    private String configMarName;
    private String configTypeDetails;
    private String itemIds;
    private String distFromTime;
    private String distToTime;
    private String hubFromTime;
    private String hubToTime;
    private int configType;
    private int isUsed;

    public int getConfigId() {
        return configId;
    }

    public void setConfigId(int configId) {
        this.configId = configId;
    }

    public String getConfigEngName() {
        return configEngName;
    }

    public void setConfigEngName(String configEngName) {
        this.configEngName = configEngName;
    }

    public String getConfigMarName() {
        return configMarName;
    }

    public void setConfigMarName(String configMarName) {
        this.configMarName = configMarName;
    }

    public String getConfigTypeDetails() {
        return configTypeDetails;
    }

    public void setConfigTypeDetails(String configTypeDetails) {
        this.configTypeDetails = configTypeDetails;
    }

    public String getItemIds() {
        return itemIds;
    }

    public void setItemIds(String itemIds) {
        this.itemIds = itemIds;
    }

    public String getDistFromTime() {
        return distFromTime;
    }

    public void setDistFromTime(String distFromTime) {
        this.distFromTime = distFromTime;
    }

    public String getDistToTime() {
        return distToTime;
    }

    public void setDistToTime(String distToTime) {
        this.distToTime = distToTime;
    }

    public String getHubFromTime() {
        return hubFromTime;
    }

    public void setHubFromTime(String hubFromTime) {
        this.hubFromTime = hubFromTime;
    }

    public String getHubToTime() {
        return hubToTime;
    }

    public void setHubToTime(String hubToTime) {
        this.hubToTime = hubToTime;
    }

    public int getConfigType() {
        return configType;
    }

    public void setConfigType(int configType) {
        this.configType = configType;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "Config{" +
                "configId=" + configId +
                ", configEngName='" + configEngName + '\'' +
                ", configMarName='" + configMarName + '\'' +
                ", configTypeDetails='" + configTypeDetails + '\'' +
                ", itemIds='" + itemIds + '\'' +
                ", distFromTime='" + distFromTime + '\'' +
                ", distToTime='" + distToTime + '\'' +
                ", hubFromTime='" + hubFromTime + '\'' +
                ", hubToTime='" + hubToTime + '\'' +
                ", configType=" + configType +
                ", isUsed=" + isUsed +
                '}';
    }
}
