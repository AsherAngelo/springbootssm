package com.ssm.service;

import com.ssm.common.services.IBaseService;
import com.ssm.entities.User;
import java.util.List;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */

public interface IUserService extends IBaseService<User> {
    List<User> userList();
    List<User> listByMapper();
    int add(User user);
    List<User> getUsers();
}
