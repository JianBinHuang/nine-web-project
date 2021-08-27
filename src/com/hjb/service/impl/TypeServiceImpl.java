package com.hjb.service.impl;

import com.hjb.dao.ITypeDao;
import com.hjb.dao.impl.TypeDaoImpl;
import com.hjb.entity.Type;
import com.hjb.service.ITypeService;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 16:22
 */
public class TypeServiceImpl implements ITypeService {

    private ITypeDao typeDao=new TypeDaoImpl();

    @Override
    public List<Type> queryAllType() {
        return typeDao.queryAllType();
    }
}
