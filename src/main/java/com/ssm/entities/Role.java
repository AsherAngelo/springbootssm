package com.ssm.entities;

import com.ssm.common.annotation.Column;
import com.ssm.common.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by 赵梦杰 on 2018/7/17.
 */
@Getter
@Setter
@ApiModel(value = "角色表")
@Table("TB_ROLE")
public class Role implements Serializable {
    @ApiModelProperty(value = "角色id")
    @Column("trid")
    private String trid;

    @ApiModelProperty(value = "角色名称")
    @Column("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色编码")
    @Column("code")
    private String code;

    private Set<User> user;
}
