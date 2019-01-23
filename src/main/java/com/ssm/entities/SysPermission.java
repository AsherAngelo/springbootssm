package com.ssm.entities;

import com.ssm.common.annotation.Blur;
import com.ssm.common.annotation.Column;
import com.ssm.common.annotation.Table;

import java.io.Serializable;
import java.util.List;

@Table("TB_SYS_PERMISSION")
public class SysPermission implements Serializable {
    @Column("id")
    private String id;//����.
    @Column("name")
    @Blur(value = {"buildFindSql","buildListSql"})
    private String name;//����.
    @Column("resource_Type")
    private String resourceType;//��Դ���ͣ�[menu|button]
    @Column("url")
    private String url;//��Դ·��.
    @Column("permission")
    private String permission; //Ȩ���ַ���,menu���ӣ�role:*��button���ӣ�role:create,role:update,role:delete,role:view
    @Column("parent_Id")
    private Long parentId; //�����
    @Column("parent_Ids")
    private String parentIds; //������б�
    @Column("available")
    private Boolean available = Boolean.FALSE;
    private List<SysRole> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}