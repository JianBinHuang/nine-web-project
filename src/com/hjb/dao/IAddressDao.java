package com.hjb.dao;

import com.hjb.entity.Address;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:53
 */
public interface IAddressDao {
    List<Address> queryAllAddressByUid(int uid);

    void update(Address address);

    void add(Address address);

    void deleteById(Integer aid);

    void setDefaultByAid(Integer aid);

    void setNotDefaultByAidAndUid(int uid,Integer aid);
}
