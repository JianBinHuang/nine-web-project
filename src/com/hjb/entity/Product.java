package com.hjb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/23 17:04
 */
public class Product implements Serializable {

    private int pid;
    private int tid;
    private String pname;
    private Date ptime;
    private String pimage;
    private BigDecimal pprice;
    private int pstate;
    private String pinfo;


    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", pname='" + pname + '\'' +
                ", ptime=" + ptime +
                ", pimage='" + pimage + '\'' +
                ", pprice=" + pprice +
                ", pstate=" + pstate +
                ", pinfo='" + pinfo + '\'' +
                '}';
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public int getPstate() {
        return pstate;
    }

    public void setPstate(int pstate) {
        this.pstate = pstate;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public Product() {
    }

    public Product(int pid, int tid, String pname, Date ptime, String pimage, BigDecimal pprice, int pstate, String pinfo) {
        this.pid = pid;
        this.tid = tid;
        this.pname = pname;
        this.ptime = ptime;
        this.pimage = pimage;
        this.pprice = pprice;
        this.pstate = pstate;
        this.pinfo = pinfo;
    }
}
