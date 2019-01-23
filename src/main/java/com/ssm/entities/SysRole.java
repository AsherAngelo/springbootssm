package com.ssm.entities;

import com.ssm.common.annotation.Column;
import com.ssm.common.annotation.Table;

import java.io.Serializable;
import java.util.List;

@Table("TB_SYS_ROLE")
public class SysRole implements Serializable {
    @Column("id")
    private String id; // ���
    @Column("role")
    private String role; // ��ɫ��ʶ�������ж�ʹ��,��"admin",�����Ψһ��:
    @Column("description")
    private String description; // ��ɫ����,UI������ʾʹ��
    @Column("available")
    private Boolean available = Boolean.FALSE; // �Ƿ����,��������ý�������Ӹ��û�
    //��ɫ -- Ȩ�޹�ϵ����Զ��ϵ;
    private List<SysPermission> permissions;
    // �û� - ��ɫ��ϵ����;
    private List<UserInfo> userInfos;// һ����ɫ��Ӧ����û�

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}