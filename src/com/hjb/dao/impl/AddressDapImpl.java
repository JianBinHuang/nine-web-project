package com.hjb.dao.impl;

import com.hjb.dao.IAddressDao;
import com.hjb.entity.Address;
import com.hjb.util.CommonUtils;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:53
 */
public class AddressDapImpl implements IAddressDao {
    @Override
    public List<Address> queryAllAddressByUid(int uid) {
        String sql = "select * from address where u_id=?";
        return CommonUtils.commonQuery(sql, Address.class, uid);
    }

    @Override
    public void update(Address address) {
        String sql="update address set a_name=?,a_phone=?,a_detail=? where a_id=?";

        CommonUtils.commonUpdate(sql,address.getAname(),address.getAphone(),address.getAdetail(),address.getAid());
    }

    @Override
    public void add(Address address) {
        String sql="insert into address values(null,?,?,?,?,?)";

        CommonUtils.commonUpdate(sql, address.getUid(),address.getAname(),address.getAphone(),address.getAdetail(),address.getAstate());
    }

    @Override
    public void deleteById(Integer aid) {
        String sql="delete from address where a_id=?";

        CommonUtils.commonUpdate(sql,aid);
    }

    @Override
    public void setDefaultByAid(Integer aid) {
        String sql="update address set a_state=1 where a_id=?";
        CommonUtils.commonUpdate(sql,aid);
    }

    @Override
    public void setNotDefaultByAidAndUid(int uid, Integer aid) {
        String sql="update address set a_state=0 where a_id !=? and u_id=?";
        CommonUtils.commonUpdate(sql,aid,uid);
    }


}