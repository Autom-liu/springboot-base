package com.edu.scnu.admin.bean;

import lombok.ToString;

import java.util.Date;
import javax.persistence.*;

@ToString
@Table(name = "idnt_menu")
public class Menu {
    /**
     * 菜单编号
     */
    @Id
    @Column(name = "`menu_id`")
    private String menuId;

    /**
     * 菜单名称
     */
    @Column(name = "`menu_name`")
    private String menuName;

    /**
     * 父菜单编号
     */
    @Column(name = "`parent_id`")
    private String parentId;

    /**
     * 菜单类型
     */
    @Column(name = "`menu_type`")
    private Integer menuType;

    /**
     * 菜单链接
     */
    @Column(name = "`url`")
    private String url;

    /**
     * 排序
     */
    @Column(name = "`order`")
    private Integer order;

    /**
     * 备注
     */
    @Column(name = "`remark`")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * 状态 0-正常
     */
    @Column(name = "`status`")
    private Boolean status;

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

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取父菜单编号
     *
     * @return parent_id - 父菜单编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父菜单编号
     *
     * @param parentId 父菜单编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取菜单类型
     *
     * @return menu_type - 菜单类型
     */
    public Integer getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型
     *
     * @param menuType 菜单类型
     */
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取菜单链接
     *
     * @return url - 菜单链接
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置菜单链接
     *
     * @param url 菜单链接
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取排序
     *
     * @return order - 排序
     */
    public Integer getOrder() {
        return order;
    }

    /**
     * 设置排序
     *
     * @param order 排序
     */
    public void setOrder(Integer order) {
        this.order = order;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取状态 0-正常
     *
     * @return status - 状态 0-正常
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常
     *
     * @param status 状态 0-正常
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }
}