package com.sunwuyou.swymcx.model;

import java.io.Serializable;

/**
 * Created by admin on
 * 2018/7/11.
 * content
 */
public class Customertype implements Serializable {
    private String id;
    private String name;
    private String pricesystemid;

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPricesystemid() {
        return pricesystemid == null ? "" : pricesystemid;
    }

    public void setPricesystemid(String pricesystemid) {
        this.pricesystemid = pricesystemid;
    }
}