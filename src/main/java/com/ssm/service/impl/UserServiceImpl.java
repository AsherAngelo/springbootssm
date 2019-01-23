package com.ssm.service.impl;

import com.ssm.common.services.BaseService;
import com.ssm.dao.IUserDao;
import com.ssm.entities.User;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
@Service(value = "userService")
//@Primary
public class UserServiceImpl extends BaseService<User> implements IUserService{

    @Autowired
    private IUserDao userDao;

    public List<User> userList(){
        return userDao.listUser();
    }
    public List<User> listByMapper(){
        return userDao.listByMapper();
    }
    public int add(User user){
        return userDao.add(user);
    }
    public List<User> getUsers(){
        return userDao.getUsers();
    }
}
