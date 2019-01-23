package com.ssm.entities;

import com.ssm.entities.Role;
import com.ssm.entities.User;

import java.io.Serializable;

/**
 * Created by 赵梦杰 on 2018/7/17.
 */
public class UserRole implements Serializable{

    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
