package com.hjb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/23 16:58
 */
public class Cart implements Serializable {

    private int cid;
    private int uid;
    private int pid;
    private BigDecimal ccount;
    private int cnum;
    private Product product;



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart() {
    }

    public Cart(int cid, int uid, int pid, BigDecimal ccount, int cnum) {
        this.cid = cid;
        this.uid = uid;
        this.pid = pid;
        this.ccount = ccount;
        this.cnum = cnum;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", ccount=" + ccount +
                ", cnum=" + cnum +
                '}';
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public BigDecimal getCcount() {

        BigDecimal count=new BigDecimal(this.cnum);

        return count.multiply(product.getPprice());
    }

    public void setCcount(BigDecimal ccount) {
        this.ccount = ccount;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }
}
