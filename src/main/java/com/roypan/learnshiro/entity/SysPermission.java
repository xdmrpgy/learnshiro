package com.roypan.learnshiro.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author by Roy Pan
 */
@Entity
@Table(name = "t_permission")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = -851773164727965491L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "t_role_permission",joinColumns = {@JoinColumn(name = "pid")},inverseJoinColumns = {@JoinColumn(name = "rid")})
    private List<SysRole> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }


}
