package com.hjb.dao;

import com.hjb.entity.User;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 15:45
 */
public interface IUserDao {

    /**
     * @Author JianBinHuang
     * @Date 2021/8/24 15:46
     * @Description 查找所有
     */
    List<User> queryAllUser();

    /**
     * @Author JianBinHuang
     * @Date 2021/8/24 15:46
     * @Description 通过id查找user
     */
    User getUserById();

   /**
    * @Author JianBinHuang
    * @Date 2021/8/24 15:47
    * @param username
    * @return
    * @Description 检查user是否存在
    */
    User checkUsername(String username);

    /**
     * 注册
     * @param user
     * @return
     */
    int register(User user);

    User queryUserByActiveCode(String activeCode);

    int updateUserStatusByActiveCode(String activeCode);
}
