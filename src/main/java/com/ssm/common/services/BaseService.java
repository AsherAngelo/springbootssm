package com.ssm.common.services;

import com.ssm.common.dao.BaseDao;
import com.ssm.common.pager.Pager;
import com.ssm.common.pager.SystemContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public abstract class BaseService<T> implements IBaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public Pager find(T t) {
        Pager<T> pager = new Pager<>();
        pager.setCurrentPage(SystemContext.getCurrentPage());
        pager.setPageSize(SystemContext.getPageSize());
        pager.setDatas(baseDao.find(t));
        pager.setTotalCount(baseDao.count(t));
        return pager;
    }

    @Override
    public int add(T bean) {
        return baseDao.add(bean);
    }

    @Override
    public int delete(T bean) {
        return baseDao.delete(bean);
    }

    @Override
    public T get(T bean) {
        return baseDao.get(bean);
    }

    @Override
    public List<T> list(T bean) {
        return baseDao.list(bean);
    }

    @Override
    public Long count(T bean) {
        return baseDao.count(bean);
    }

    @Override
    public int update(T bean) {
        return baseDao.update(bean);
    }

//    @Override
//    public String getSeq(String seqName) {
//        return baseDao.getSeq(seqName);
//    }
}
