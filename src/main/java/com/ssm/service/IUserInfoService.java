package com.ssm.service;


import com.ssm.common.services.IBaseService;
import com.ssm.entities.UserInfo;

public interface IUserInfoService extends IBaseService<UserInfo> {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

    UserInfo createUser(UserInfo user);
}