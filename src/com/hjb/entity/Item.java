package com.hjb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/23 17:00
 */
public class Item implements Serializable {

    private int iid;
    private String oid;
    private int pid;
    private BigDecimal icount;
    private int inum;
    //展示商品详情需要用到product对象
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item(int iid, String oid, int pid, BigDecimal icount, int inum) {
        this.iid = iid;
        this.oid = oid;
        this.pid = pid;
        this.icount = icount;
        this.inum = inum;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", oid='" + oid + '\'' +
                ", pid=" + pid +
                ", icount=" + icount +
                ", inum=" + inum +
                '}';
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public BigDecimal getIcount() {
        return icount;
    }

    public void setIcount(BigDecimal icount) {
        this.icount = icount;
    }

    public int getInum() {
        return inum;
    }

    public void setInum(int inum) {
        this.inum = inum;
    }

    public Item() {
    }
}
