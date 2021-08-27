package com.hjb.entity;

import java.io.Serializable;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/23 17:06
 */
public class Type implements Serializable {

    private int tid;
    private String tname;
    private String tinfo;


    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tinfo='" + tinfo + '\'' +
                '}';
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTinfo() {
        return tinfo;
    }

    public void setTinfo(String tinfo) {
        this.tinfo = tinfo;
    }

    public Type() {
    }

    public Type(int tid, String tname, String tinfo) {
        this.tid = tid;
        this.tname = tname;
        this.tinfo = tinfo;
    }
}
