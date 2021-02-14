package com.edu.scnu.idnt.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "idnt_user")
public class User {
    /**
     * 登陆用户名，唯一主键
     */
    @Id
    @Column(name = "`user_id`")
    private String userId;

    /**
     * 用户名
     */
    @Column(name = "`user_name`")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 手机号
     */
    @Column(name = "`mobile`")
    private String mobile;

    /**
     * 办公电话
     */
    @Column(name = "`office_phone`")
    private String officePhone;

    /**
     * 邮箱
     */
    @Column(name = "`email`")
    private String email;

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
     * 状态 0-正常 1-初次创建 2-休假/暂停使用 3-离职
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 获取登陆用户名，唯一主键
     *
     * @return user_id - 登陆用户名，唯一主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置登陆用户名，唯一主键
     *
     * @param userId 登陆用户名，唯一主键
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取办公电话
     *
     * @return office_phone - 办公电话
     */
    public String getOfficePhone() {
        return officePhone;
    }

    /**
     * 设置办公电话
     *
     * @param officePhone 办公电话
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
     * 获取状态 0-正常 1-初次创建 2-休假/暂停使用 3-离职
     *
     * @return status - 状态 0-正常 1-初次创建 2-休假/暂停使用 3-离职
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常 1-初次创建 2-休假/暂停使用 3-离职
     *
     * @param status 状态 0-正常 1-初次创建 2-休假/暂停使用 3-离职
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}