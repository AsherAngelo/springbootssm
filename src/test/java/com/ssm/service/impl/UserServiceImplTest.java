package com.ssm.service.impl;

import com.ssm.dao.IUserDao;
import com.ssm.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 赵梦杰 on 2018/7/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private IUserDao userDao;

    @Test
    public void userList() throws Exception {
        List<User> userInfoList = userDao.getUsers();
        System.out.println(userInfoList);
        Assert.assertEquals(userInfoList.size()>0,true);
    }

    @Test
    public void listByMapper() throws Exception {
    }

    @Test
    public void add() throws Exception {
    }

    @Test
    public void getUsers() throws Exception {
    }


}
