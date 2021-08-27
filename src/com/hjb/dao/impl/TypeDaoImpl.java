package com.hjb.dao.impl;

import com.hjb.dao.ITypeDao;
import com.hjb.entity.Type;
import com.hjb.util.CommonUtils;

import java.util.List;

/**
 * @Author JianBinHuang
 * @Description
 * @Date 2021/8/25 16:21
 */
public class TypeDaoImpl implements ITypeDao {
    @Override
    public List<Type> queryAllType() {
        String sql="select * from type";
        return CommonUtils.commonQuery(sql, Type.class);
    }
}
