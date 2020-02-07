package com.edu.scnu.baseinfo.bean;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "zw_account")
public class Account implements Serializable {
    @Id
    @Column(name = "`acc_id`")
    private Integer accId;

    /**
     * 系统类型
     */
    @Column(name = "`acc_sys_type`")
    private String accSysType;

    /**
     * 用户类型
     */
    @Column(name = "`acc_user_type`")
    private String accUserType;

    /**
     * 账户名称
     */
    @Column(name = "`acc_name`")
    private String accName;

    /**
     * 账户图标
     */
    @Column(name = "`acc_icon`")
    private String accIcon;

    /**
     * 账户备注
     */
    @Column(name = "`acc_remark`")
    private String accRemark;

    /**
     * 账户金额
     */
    @Column(name = "`acc_amount`")
    private Long accAmount;

    /**
     * 是否有子账户
     */
    @Column(name = "`acc_has_child`")
    private Boolean accHasChild;

    /**
     * 是否可支付
     */
    @Column(name = "`acc_can_pay`")
    private Boolean accCanPay;

    /**
     * 是否可转账
     */
    @Column(name = "`acc_can_transfer`")
    private Boolean accCanTransfer;

    /**
     * 父账户名称
     */
    @Column(name = "`acc_parent_id`")
    private Integer accParentId;

    @Column(name = "`is_del`")
    private Boolean isDel;

    private static final long serialVersionUID = 1L;

    /**
     * @return acc_id
     */
    public Integer getAccId() {
        return accId;
    }

    /**
     * @param accId
     */
    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    /**
     * 获取系统类型
     *
     * @return acc_sys_type - 系统类型
     */
    public String getAccSysType() {
        return accSysType;
    }

    /**
     * 设置系统类型
     *
     * @param accSysType 系统类型
     */
    public void setAccSysType(String accSysType) {
        this.accSysType = accSysType == null ? null : accSysType.trim();
    }

    /**
     * 获取用户类型
     *
     * @return acc_user_type - 用户类型
     */
    public String getAccUserType() {
        return accUserType;
    }

    /**
     * 设置用户类型
     *
     * @param accUserType 用户类型
     */
    public void setAccUserType(String accUserType) {
        this.accUserType = accUserType == null ? null : accUserType.trim();
    }

    /**
     * 获取账户名称
     *
     * @return acc_name - 账户名称
     */
    public String getAccName() {
        return accName;
    }

    /**
     * 设置账户名称
     *
     * @param accName 账户名称
     */
    public void setAccName(String accName) {
        this.accName = accName == null ? null : accName.trim();
    }

    /**
     * 获取账户图标
     *
     * @return acc_icon - 账户图标
     */
    public String getAccIcon() {
        return accIcon;
    }

    /**
     * 设置账户图标
     *
     * @param accIcon 账户图标
     */
    public void setAccIcon(String accIcon) {
        this.accIcon = accIcon == null ? null : accIcon.trim();
    }

    /**
     * 获取账户备注
     *
     * @return acc_remark - 账户备注
     */
    public String getAccRemark() {
        return accRemark;
    }

    /**
     * 设置账户备注
     *
     * @param accRemark 账户备注
     */
    public void setAccRemark(String accRemark) {
        this.accRemark = accRemark == null ? null : accRemark.trim();
    }

    /**
     * 获取账户金额
     *
     * @return acc_amount - 账户金额
     */
    public Long getAccAmount() {
        return accAmount;
    }

    /**
     * 设置账户金额
     *
     * @param accAmount 账户金额
     */
    public void setAccAmount(Long accAmount) {
        this.accAmount = accAmount;
    }

    /**
     * 获取是否有子账户
     *
     * @return acc_has_child - 是否有子账户
     */
    public Boolean getAccHasChild() {
        return accHasChild;
    }

    /**
     * 设置是否有子账户
     *
     * @param accHasChild 是否有子账户
     */
    public void setAccHasChild(Boolean accHasChild) {
        this.accHasChild = accHasChild;
    }

    /**
     * 获取是否可支付
     *
     * @return acc_can_pay - 是否可支付
     */
    public Boolean getAccCanPay() {
        return accCanPay;
    }

    /**
     * 设置是否可支付
     *
     * @param accCanPay 是否可支付
     */
    public void setAccCanPay(Boolean accCanPay) {
        this.accCanPay = accCanPay;
    }

    /**
     * 获取是否可转账
     *
     * @return acc_can_transfer - 是否可转账
     */
    public Boolean getAccCanTransfer() {
        return accCanTransfer;
    }

    /**
     * 设置是否可转账
     *
     * @param accCanTransfer 是否可转账
     */
    public void setAccCanTransfer(Boolean accCanTransfer) {
        this.accCanTransfer = accCanTransfer;
    }

    /**
     * 获取父账户名称
     *
     * @return acc_parent_id - 父账户名称
     */
    public Integer getAccParentId() {
        return accParentId;
    }

    /**
     * 设置父账户名称
     *
     * @param accParentId 父账户名称
     */
    public void setAccParentId(Integer accParentId) {
        this.accParentId = accParentId;
    }

    /**
     * @return is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }
}