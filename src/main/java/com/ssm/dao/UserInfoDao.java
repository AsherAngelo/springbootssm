package com.ssm.dao;

import com.ssm.common.dao.BaseDao;
import com.ssm.entities.UserInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends BaseDao<UserInfo> {
    /**ͨ��username�����û���Ϣ;*/
    public UserInfo findByUsername(String username);
    public UserInfo save(UserInfo userInfo);
}