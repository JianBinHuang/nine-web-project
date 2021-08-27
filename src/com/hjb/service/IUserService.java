package com.hjb.service;

import com.hjb.entity.User;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 15:52
 */
public interface IUserService {

    /**
     * 查询所有
     * @return
     */
    List<User> queryAllUser();

    /**
     * 通过id查询
     * @return
     */
    User getUserById();

    /**
     * 检查用户是否已存在
     * @param username
     * @return
     */
    boolean checkUserName(String username);

    /**
     * 注册
     * @param user
     * @return
     */
    int register(User user);


    int active(String activeCode);

    User checkLogin(User user);
}
