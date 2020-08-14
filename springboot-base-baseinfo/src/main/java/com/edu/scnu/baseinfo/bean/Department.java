package com.edu.scnu.baseinfo.bean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "idnt_dept")
public class Department {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`dept_no`")
    private Integer deptNo;

    @Column(name = "`dept_name`")
    private String deptName;

    @Column(name = "`dept_type`")
    private String deptType;

    @Column(name = "`parent_id`")
    private Integer parentId;

    @Column(name = "`parent_no`")
    private Integer parentNo;

    @Column(name = "`applist`")
    private String applist;

    /**
     * 创建时间
     */
    @Column(name = "`create_time`")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "`update_time`")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return dept_no
     */
    public Integer getDeptNo() {
        return deptNo;
    }

    /**
     * @param deptNo
     */
    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    /**
     * @return dept_name
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return dept_type
     */
    public String getDeptType() {
        return deptType;
    }

    /**
     * @param deptType
     */
    public void setDeptType(String deptType) {
        this.deptType = deptType;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return parent_no
     */
    public Integer getParentNo() {
        return parentNo;
    }

    /**
     * @param parentNo
     */
    public void setParentNo(Integer parentNo) {
        this.parentNo = parentNo;
    }

    /**
     * @return applist
     */
    public String getApplist() {
        return applist;
    }

    /**
     * @param applist
     */
    public void setApplist(String applist) {
        this.applist = applist;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}