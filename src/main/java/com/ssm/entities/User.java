package com.ssm.entities;

import com.ssm.common.annotation.Blur;
import com.ssm.common.annotation.Column;
import com.ssm.common.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by 赵梦杰 on 2018/7/16.
 */

@Setter
@Getter
@ApiModel(value = "用户名")
@Table("TB_USER")
public class User implements Serializable {
    @ApiModelProperty(value = "用户id")
    @Column("tuid")
    private String tuid;

    @Blur(value = {"buildFindSql","buildListSql"})
    @ApiModelProperty(value = "用户姓名")
    @Column("name")
    private String name;

    @ApiModelProperty(value = "用户年龄")
    @Column("age")
    private Integer age;

    @ApiModelProperty(value = "用户性别")
    @Column("sex")
    private Boolean sex;

    @ApiModelProperty(value = "用户手机")
    @Column("mobile")
    private String mobile;

    @ApiModelProperty(value = "用户备注")
    @Column("remark")
    private String remark;

    private Set<Role> role;

    @Override
    public String toString() {
        return "User{" +
                "tuid='" + tuid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", remark='" + remark + '\'' +
                ", role=" + role +
                '}';
    }
}
