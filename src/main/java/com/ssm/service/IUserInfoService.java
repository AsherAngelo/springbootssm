package com.ssm.service;


import com.ssm.common.services.IBaseService;
import com.ssm.entities.UserInfo;

public interface IUserInfoService extends IBaseService<UserInfo> {
    /**ͨ��username�����û���Ϣ;*/
    UserInfo findByUsername(String username);

    UserInfo createUser(UserInfo user);
}