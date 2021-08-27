package com.hjb.service.impl;

import com.hjb.dao.IAddressDao;
import com.hjb.dao.impl.AddressDapImpl;
import com.hjb.entity.Address;
import com.hjb.service.IAddressService;
import com.hjb.util.CommonUtils;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/26 15:56
 */
public class AddressServiceImpl implements IAddressService {

    private IAddressDao addressDao=new AddressDapImpl();

    @Override
    public List<Address> queryAllAddressByUid(int uid) {
        return addressDao.queryAllAddressByUid(uid);
    }

    @Override
    public void update(Address address) {
      addressDao.update(address);
    }

    @Override
    public void add(Address address) {
        addressDao.add(address);
    }

    @Override
    public void deleteById(Integer aid) {
        addressDao.deleteById(aid);
    }

    @Override
    public void setDefault(int uid, Integer aid) {

        //当前用户的aid对应的默认值设置为1
        addressDao.setDefaultByAid(aid);
        //当前用户的非aid的其他地址默认值设置为0
        addressDao.setNotDefaultByAidAndUid(uid, aid);

    }
}
