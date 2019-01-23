package com.ssm.service.impl;

import com.ssm.common.services.BaseService;
import com.ssm.common.utils.PasswordHelper;
import com.ssm.dao.UserInfoDao;
import com.ssm.entities.User;
import com.ssm.entities.UserInfo;
import com.ssm.service.IUserInfoService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service(value = "userInfoService")
//@Primary
public class UserInfoServiceImpl extends BaseService<UserInfo> implements IUserInfoService ,InitializingBean {

    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public UserInfo createUser(UserInfo user){
        passwordHelper.encryptPassword(user);
        return userInfoDao.save(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("我是回掉函数.....................");
    }
}