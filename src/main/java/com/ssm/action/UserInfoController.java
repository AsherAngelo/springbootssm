package com.ssm.action;

import com.ssm.entities.UserInfo;
import com.ssm.service.IUserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
    @RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;
    /**
     * �û���ѯ.
     * @return
     */
    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")//Ȩ�޹���;
    public String userInfo(){
        return "userInfo";
    }

    /**
     * �û�����;
     * @return
     */
    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//Ȩ�޹���;
    public String userInfoAdd(UserInfo userInfo){
        //UserInfo userInfo1 =  userInfoService.createUser(userInfo);
        //System.out.println(userInfo1);
        return "userInfoAdd";
    }

    /**
     * �û�ɾ��;
     * @return
     */
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//Ȩ�޹���;
    public String userDel(){
        return "userInfoDel";
    }
}