package com.hjb.service.impl;

import com.hjb.dao.IUserDao;
import com.hjb.dao.impl.UserDaoImpl;
import com.hjb.entity.User;
import com.hjb.service.IUserService;
import com.hjb.util.Constants;
import com.hjb.util.EmailUtils;
import com.hjb.util.MD5Utils;
import sun.security.provider.MD5;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/24 15:54
 */
public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public List<User> queryAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public User getUserById() {
        return userDao.getUserById();
    }

    @Override
    public boolean checkUserName(String username) {
        //通过用户查询用户对象
        User user = userDao.checkUsername(username);

        //用户名存在
        return user != null;
    }

    @Override
    public int register(User user) {
        int result = userDao.register(user);
        if (result > 0) {
            //注册成功
            //发送激活邮件
            EmailUtils.sendEmail(user);
        }
        return result;
    }

    @Override
    public int active(String activeCode) {
        //通过激活码查询用户信息
        User user = userDao.queryUserByActiveCode(activeCode);

        if (user == null) {
            //激活失败
            return Constants.ACTIVE_FAIL;
        }
        if (user.getUstatus().equals("1")) {
            return Constants.ACTIVE_ALREADY;
        }
        int result = userDao.updateUserStatusByActiveCode(activeCode);

        if(result>0){
            //激活成功
            return Constants.ACTIVE_SUCCESS;
        }

        return 0;
    }

    @Override
    public User checkLogin(User user) {
        /**
         * 对密码进行加密
         */
        String pwd= MD5Utils.md5(user.getUpassword());
        //通过用户名查询用户对象
        User users=userDao.checkUsername(user.getUname());
        if(users!=null){
            if(users.getUpassword().equals(pwd)){
                //用户存在，登录没问题
                return users;
            }
        }
        return null;
    }
}
