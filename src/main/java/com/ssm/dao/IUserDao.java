package com.ssm.dao;

import com.ssm.common.dao.BaseDao;
import com.ssm.entities.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
@Repository
public interface IUserDao extends BaseDao<User> {
    @Select("SELECT * FROM TB_USER")
    List<User> listUser();

    List<User> listByMapper();

    List<User> getUsers();

//    @Insert("INSERT INTO TB_USER VALUES(#{id},#{name},#{age},#{sex},#{mobile},#{remark})")
//    @SelectKey(before = true, statement = "SELECT SYS_GUID() AS ID FROM DUAL", statementType = StatementType.STATEMENT, resultType = String.class, keyProperty = "id")
//    @Options(useGeneratedKeys = true)
//    int add(User user);
}
