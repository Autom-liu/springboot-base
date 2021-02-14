package com.edu.scnu.idnt.bean;

import java.util.Date;
import javax.persistence.*;

@Table(name = "idnt_department")
public class Department {
    /**
     * 机构编号
     */
    @Id
    @Column(name = "`dept_id`")
    private String deptId;

    /**
     * 机构名称
     */
    @Column(name = "`dept_name`")
    private String deptName;

    /**
     * 父机构编号
     */
    @Column(name = "`parent_id`")
    private String parentId;

    /**
     * 机构级别
     */
    @Column(name = "`dept_level`")
    private Integer deptLevel;

    /**
     * 机构类型(暂不使用)
     */
    @Column(name = "`dept_type`")
    private Boolean deptType;

    /**
     * 机构管理员
     */
    @Column(name = "`dept_manager`")
    private String deptManager;

    /**
     * 暂不使用
     */
    @Column(name = "`dept_category`")
    private String deptCategory;

    /**
     * 暂不使用
     */
    @Column(name = "`dept_property`")
    private String deptProperty;

    /**
     * 机构电话
     */
    @Column(name = "`phone`")
    private String phone;

    /**
     * 机构地址
     */
    @Column(name = "`address`")
    private String address;

    /**
     * 生效日期
     */
    @Column(name = "`effective_time`")
    private Date effectiveTime;

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
    private Integer status;

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
     * 获取机构名称
     *
     * @return dept_name - 机构名称
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * 设置机构名称
     *
     * @param deptName 机构名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * 获取父机构编号
     *
     * @return parent_id - 父机构编号
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父机构编号
     *
     * @param parentId 父机构编号
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取机构级别
     *
     * @return dept_level - 机构级别
     */
    public Integer getDeptLevel() {
        return deptLevel;
    }

    /**
     * 设置机构级别
     *
     * @param deptLevel 机构级别
     */
    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    /**
     * 获取机构类型(暂不使用)
     *
     * @return dept_type - 机构类型(暂不使用)
     */
    public Boolean getDeptType() {
        return deptType;
    }

    /**
     * 设置机构类型(暂不使用)
     *
     * @param deptType 机构类型(暂不使用)
     */
    public void setDeptType(Boolean deptType) {
        this.deptType = deptType;
    }

    /**
     * 获取机构管理员
     *
     * @return dept_manager - 机构管理员
     */
    public String getDeptManager() {
        return deptManager;
    }

    /**
     * 设置机构管理员
     *
     * @param deptManager 机构管理员
     */
    public void setDeptManager(String deptManager) {
        this.deptManager = deptManager;
    }

    /**
     * 获取暂不使用
     *
     * @return dept_category - 暂不使用
     */
    public String getDeptCategory() {
        return deptCategory;
    }

    /**
     * 设置暂不使用
     *
     * @param deptCategory 暂不使用
     */
    public void setDeptCategory(String deptCategory) {
        this.deptCategory = deptCategory;
    }

    /**
     * 获取暂不使用
     *
     * @return dept_property - 暂不使用
     */
    public String getDeptProperty() {
        return deptProperty;
    }

    /**
     * 设置暂不使用
     *
     * @param deptProperty 暂不使用
     */
    public void setDeptProperty(String deptProperty) {
        this.deptProperty = deptProperty;
    }

    /**
     * 获取机构电话
     *
     * @return phone - 机构电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置机构电话
     *
     * @param phone 机构电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取机构地址
     *
     * @return address - 机构地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置机构地址
     *
     * @param address 机构地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取生效日期
     *
     * @return effective_time - 生效日期
     */
    public Date getEffectiveTime() {
        return effectiveTime;
    }

    /**
     * 设置生效日期
     *
     * @param effectiveTime 生效日期
     */
    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
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
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态 0-正常
     *
     * @param status 状态 0-正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}