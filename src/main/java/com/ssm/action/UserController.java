package com.ssm.action;

import com.ssm.common.controller.BaseController;
import com.ssm.common.pager.Pager;
import com.ssm.entities.User;
import com.ssm.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */
@RestController
@Api(value = "UserController", description = "用户模块")
public class UserController{

    @Autowired
    private IUserService userService;

    @GetMapping("/find")
    @ApiOperation(value = "用户列表查询", response = User.class)
    public List<User> findUser(){
        List<User> list = userService.userList();
        return list;
    }

    @GetMapping("/find2")
    @ApiOperation(value = "用户列表查询2", response = User.class)
    public List<User> findByMapperUser(){
        List<User> list = userService.listByMapper();
        return list;
    }

    @PostMapping("/find3")
    @ApiOperation(value = "用户列表查询3", response = User.class)
    public List<User> listByBaseUser(@ModelAttribute User user){
        List<User> list = userService.list(user);
        return list;
    }

    @PostMapping("/find4")
    @ApiOperation(value = "用户列表查询3", response = User.class)
    @ApiImplicitParam(name="options",value = "分页标记",dataType = "String",paramType = "query",required = false,
            defaultValue = "{\"search\":{},\"sort\":{},\"page\":{\"totalCount\":180,\"currentPage\":1,\"pageSize\":5}}")
    public Pager<User> findByBaseUser(@ModelAttribute User user){
        Pager<User> list = userService.find(user);
        return list;
    }

    @PostMapping("/post")
    @ApiOperation(value = "用户添加", response = User.class)
    public int add(@ModelAttribute User user){
        return userService.add(user);
    }

    @PostMapping("/get")
    @ApiOperation(value = "用户列表查询4", response = User.class)
    public List<User> getUsers(){
        return userService.getUsers();
    }


}
