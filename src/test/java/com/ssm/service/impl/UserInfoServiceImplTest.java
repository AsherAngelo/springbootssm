package com.ssm.service.impl;

import com.ssm.common.utils.PasswordHelper;
import com.ssm.dao.UserInfoDao;
import com.ssm.entities.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 赵梦杰 on 2018/7/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceImplTest {

    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void findByUsername() throws Exception {
        UserInfo userInfo = userInfoDao.findByUsername("admin");
        System.out.println(userInfo.getName());
        Assert.assertEquals("1",userInfo.getId());
    }

    @Test
    public void createUser() throws Exception {
    }

    @Test
    public void afterPropertiesSet() throws Exception {
    }


}
