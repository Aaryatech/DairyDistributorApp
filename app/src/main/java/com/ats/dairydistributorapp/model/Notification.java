package com.ats.dairydistributorapp.model;

public class Notification {

    private int notifiId;
    private int notifiType;
    private int notifiFrom;
    private int notifiTo;
    private String notifiDate;
    private String notifiDatetime;
    private String notifiText;
    private int isRead;

    public int getNotifiId() {
        return notifiId;
    }

    public void setNotifiId(int notifiId) {
        this.notifiId = notifiId;
    }

    public int getNotifiType() {
        return notifiType;
    }

    public void setNotifiType(int notifiType) {
        this.notifiType = notifiType;
    }

    public int getNotifiFrom() {
        return notifiFrom;
    }

    public void setNotifiFrom(int notifiFrom) {
        this.notifiFrom = notifiFrom;
    }

    public int getNotifiTo() {
        return notifiTo;
    }

    public void setNotifiTo(int notifiTo) {
        this.notifiTo = notifiTo;
    }

    public String getNotifiDate() {
        return notifiDate;
    }

    public void setNotifiDate(String notifiDate) {
        this.notifiDate = notifiDate;
    }

    public String getNotifiDatetime() {
        return notifiDatetime;
    }

    public void setNotifiDatetime(String notifiDatetime) {
        this.notifiDatetime = notifiDatetime;
    }

    public String getNotifiText() {
        return notifiText;
    }

    public void setNotifiText(String notifiText) {
        this.notifiText = notifiText;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notifiId=" + notifiId +
                ", notifiType=" + notifiType +
                ", notifiFrom=" + notifiFrom +
                ", notifiTo=" + notifiTo +
                ", notifiDate='" + notifiDate + '\'' +
                ", notifiDatetime='" + notifiDatetime + '\'' +
                ", notifiText='" + notifiText + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
