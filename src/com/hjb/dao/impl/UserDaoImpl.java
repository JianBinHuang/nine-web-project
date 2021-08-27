package com.hjb.dao.impl;

import com.hjb.dao.IUserDao;
import com.hjb.entity.User;
import com.hjb.util.CommonUtils;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 15:49
 */
public class UserDaoImpl implements IUserDao {
    @Override
    public List<User> queryAllUser() {
        String sql="select * from user";
        return CommonUtils.commonQuery(sql, User.class );
    }

    @Override
    public User getUserById() {
        String sql="select * from user where u_id=?";
        //测试检查单个的
        return CommonUtils.querySingleInstance(sql, User.class, 2);
    }

    @Override
    public User checkUsername(String username) {
        String sql="select * from user where u_name=?";
        return CommonUtils.querySingleInstance(sql, User.class, username);
    }

    @Override
    public int register(User user) {
        String sql="insert into user values(null,?,?,?,?,?,?,?)";

        return CommonUtils.commonUpdate(sql, user.getUname(),
                user.getUpassword(),user.getUemail(),
                user.getUsex(),user.getUstatus(),
                user.getUcode(),user.getUrole());
    }

    @Override
    public User queryUserByActiveCode(String activeCode) {
        String sql="select * from user where u_code=? ";
        return CommonUtils.querySingleInstance(sql, User.class, activeCode);
    }

    @Override
    public int updateUserStatusByActiveCode(String activeCode) {
       String sql="update user set u_status=1 where u_code = ?";
        return CommonUtils.commonUpdate(sql, activeCode);
    }
}
