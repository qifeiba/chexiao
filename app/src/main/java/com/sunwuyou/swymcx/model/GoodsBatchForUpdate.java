package com.sunwuyou.swymcx.model;

/**
 * Created by liupiao on
 * 2018/7/11.
 * content
 */
public class GoodsBatchForUpdate {
    private String batch;

    public String getBatch() {
        return batch == null ? "" : batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getGoodsid() {
        return goodsid == null ? "" : goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getProductiondate() {
        return productiondate == null ? "" : productiondate;
    }

    public void setProductiondate(String productiondate) {
        this.productiondate = productiondate;
    }

    public double getStocknumber() {
        return stocknumber;
    }

    public void setStocknumber(double stocknumber) {
        this.stocknumber = stocknumber;
    }

    private String goodsid;
    private String productiondate;
    private double stocknumber;
}