package com.ssm.shiro;

import com.ssm.entities.SysPermission;
import com.ssm.entities.SysRole;
import com.ssm.entities.UserInfo;
import com.ssm.service.IUserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private IUserInfoService userInfoService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("Ȩ������-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        for(SysRole role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRole());
            for(SysPermission p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /*��Ҫ���������������֤�ģ�Ҳ����˵��֤�û�������˺ź������Ƿ���ȷ��*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //��ȡ�û���������˺�.
        String username = (String)token.getPrincipal();
        System.out.println("�û���:"+username);
        //System.out.println(token.getCredentials());
        System.out.println("��֪����ɶ:"+String.valueOf((char[])token.getCredentials()));
        //ͨ��username�����ݿ��в��� User��������ҵ���û�ҵ�.
        //ʵ����Ŀ�У�������Ը���ʵ����������棬���������Shiro�Լ�Ҳ����ʱ�������ƣ�2�����ڲ����ظ�ִ�и÷���
        UserInfo userInfo = userInfoService.findByUsername(username);
        System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //�û���
                userInfo.getPassword(), //����
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

}