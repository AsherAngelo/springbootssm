package com.ssm.common.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
//@Repository
public interface BaseDao<T> {

    @InsertProvider(type = BaseSqlProvider.class, method = "buildAddSql")
    @SelectKey(before = true, statement = "SELECT SYS_GUID() AS ID FROM DUAL", statementType = StatementType.STATEMENT, resultType = String.class, keyProperty = "id")
    @Options(useGeneratedKeys = true)
    int add(T bean);

    @DeleteProvider(type = BaseSqlProvider.class, method = "buildDeleteSql")
    int delete(T bean);

    @UpdateProvider(type = BaseSqlProvider.class, method = "buildUpdateSql")
    int update(T bean);

    @SelectProvider(type = BaseSqlProvider.class, method = "buildGetSql")
    T get(T bean);

    @SelectProvider(type = BaseSqlProvider.class, method = "buildListSql")
    List<T> list(T bean);

    @SelectProvider(type = BaseSqlProvider.class, method = "buildFindSql")
    List<T> find(T bean);

    @SelectProvider(type = BaseSqlProvider.class, method = "buildCountSql")
    Long count(T bean);

//    @UpdateProvider(type = BaseSqlProvider.class, method = "buildSeqSql")
//    String getSeq(String seqName);
}
