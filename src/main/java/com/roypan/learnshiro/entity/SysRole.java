package com.roypan.learnshiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author by Roy Pan
 */
@Entity
@Table(name = "t_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 8014235901568203952L;

    @Id
    @GeneratedValue
    private Integer id;
    private String role;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",joinColumns = {@JoinColumn(name = "rid")},inverseJoinColumns = {@JoinColumn(name = "pid")})
    private List<SysPermission> permissionList;

    @ManyToMany
    @JoinTable(name = "t_user_role",joinColumns = {@JoinColumn(name = "rid")},inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<User> userList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
