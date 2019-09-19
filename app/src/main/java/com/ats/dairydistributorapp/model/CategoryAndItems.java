package com.ats.dairydistributorapp.model;

import java.util.List;

public class CategoryAndItems {

    List<GetCatItemList> catItemLists;
    GetOrderHeader getOrderHeader;
    Config config;

    public List<GetCatItemList> getCatItemLists() {
        return catItemLists;
    }

    public void setCatItemLists(List<GetCatItemList> catItemLists) {
        this.catItemLists = catItemLists;
    }

    public GetOrderHeader getGetOrderHeader() {
        return getOrderHeader;
    }

    public void setGetOrderHeader(GetOrderHeader getOrderHeader) {
        this.getOrderHeader = getOrderHeader;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "CategoryAndItems{" +
                "catItemLists=" + catItemLists +
                ", getOrderHeader=" + getOrderHeader +
                ", config=" + config +
                '}';
    }
}
