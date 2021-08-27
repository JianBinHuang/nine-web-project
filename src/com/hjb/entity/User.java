package com.hjb.entity;

import java.io.Serializable;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/23 17:07
 */
public class User implements Serializable {

    private int uid;
    private String uname;
    private String upassword;
    private String uemail;
    private String usex;
    private String ustatus;
    private String ucode;
    private int urole;



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public int getUrole() {
        return urole;
    }

    public void setUrole(int urole) {
        this.urole = urole;
    }

    public User() {
    }

    public User(String uname, String upassword) {
        this.uname = uname;
        this.upassword = upassword;
    }

    public User(int uid, String uname, String upassword, String uemail, String usex, String ustatus, String ucode, int urole) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.uemail = uemail;
        this.usex = usex;
        this.ustatus = ustatus;
        this.ucode = ucode;
        this.urole = urole;
    }
}
