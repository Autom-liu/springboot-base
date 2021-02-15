package com.edu.scnu.admin.bean;

import lombok.ToString;

import javax.persistence.*;

@ToString
@Table(name = "idnt_permission")
public class Permission {
    /**
     * 用户编号
     */
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 机构编号
     */
    @Column(name = "`dept_id`")
    private String deptId;

    /**
     * 角色编号
     */
    @Column(name = "`role_id`")
    private String roleId;

    /**
     * 菜单编号
     */
    @Column(name = "`menu_id`")
    private String menuId;

    /**
     * 获取用户编号
     *
     * @return user_id - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取机构编号
     *
     * @return dept_id - 机构编号
     */
    public String getDeptId() {
        return deptId;
    }

    /**
     * 设置机构编号
     *
     * @param deptId 机构编号
     */
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取角色编号
     *
     * @return role_id - 角色编号
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置角色编号
     *
     * @param roleId 角色编号
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取菜单编号
     *
     * @return menu_id - 菜单编号
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置菜单编号
     *
     * @param menuId 菜单编号
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}