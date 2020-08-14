package com.edu.scnu.baseinfo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "idnt_app")
public class App {
    /**
     * 主键
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 应用简称
     */
    @Column(name = "`simple_name`")
    private String simpleName;

    /**
     * 全称
     */
    @Column(name = "`full_name`")
    private String fullName;

    /**
     * 简介
     */
    @Column(name = "`description`")
    private String description;

    /**
     * 管理员
     */
    @Column(name = "`manager`")
    private String manager;

    /**
     * 管理员联系方式
     */
    @Column(name = "`manager_mobile`")
    private String managerMobile;

    /**
     * 访问域名
     */
    @Column(name = "`domain`")
    private String domain;

    /**
     * 所属企业
     */
    @Column(name = "`company`")
    private String company;

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
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取应用简称
     *
     * @return simple_name - 应用简称
     */
    public String getSimpleName() {
        return simpleName;
    }

    /**
     * 设置应用简称
     *
     * @param simpleName 应用简称
     */
    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    /**
     * 获取全称
     *
     * @return full_name - 全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置全称
     *
     * @param fullName 全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取简介
     *
     * @return description - 简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置简介
     *
     * @param description 简介
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取管理员
     *
     * @return manager - 管理员
     */
    public String getManager() {
        return manager;
    }

    /**
     * 设置管理员
     *
     * @param manager 管理员
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * 获取管理员联系方式
     *
     * @return manager_mobile - 管理员联系方式
     */
    public String getManagerMobile() {
        return managerMobile;
    }

    /**
     * 设置管理员联系方式
     *
     * @param managerMobile 管理员联系方式
     */
    public void setManagerMobile(String managerMobile) {
        this.managerMobile = managerMobile;
    }

    /**
     * 获取访问域名
     *
     * @return domain - 访问域名
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 设置访问域名
     *
     * @param domain 访问域名
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 获取所属企业
     *
     * @return company - 所属企业
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置所属企业
     *
     * @param company 所属企业
     */
    public void setCompany(String company) {
        this.company = company;
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
}