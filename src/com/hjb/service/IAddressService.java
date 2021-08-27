package com.hjb.service;

import com.hjb.entity.Address;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:55
 */
public interface IAddressService {
    List<Address> queryAllAddressByUid(int uid);

    void update(Address address);

    void add(Address address);

    void deleteById(Integer aid);

    void setDefault(int uid,Integer aid);
}
