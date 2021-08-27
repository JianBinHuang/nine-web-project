package com.hjb.util;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 16:02
 */
public class Constants {

    public static final String USERNAME_EXISTS="1";
    public static final String USERNAME_NOT_EXISTS="0";


    //用户激活状态
    public static final String USER_ACTIVE="1";
    public static final String USER_NOT_ACTIVE="0";

    //用户的角色
    public static final int USER_CUSTOMER=0;
    public static final int USER_ADMIN=1;

    //邮件激活账户的状态
    /**
     * 0  失败
     * 1  已经存在
     * 2  成功
     */
    public static final int ACTIVE_FAIL=0;
    public static final int ACTIVE_ALREADY=1;
    public static final int ACTIVE_SUCCESS=2;
}
