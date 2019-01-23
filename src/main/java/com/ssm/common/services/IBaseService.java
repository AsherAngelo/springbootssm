package com.ssm.common.services;
import com.ssm.common.pager.Pager;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> extends Serializable {

    int add(T bean);

    int delete(T bean);

    T get(T bean);

    List<T> list(T bean);

    Pager<T> find(T bean);

    Long count(T bean);

    int update(T bean);

//    String getSeq(String seqName);
}
