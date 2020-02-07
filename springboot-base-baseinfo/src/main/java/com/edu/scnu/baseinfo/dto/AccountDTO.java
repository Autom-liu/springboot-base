package com.edu.scnu.baseinfo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer accId;

    private String accSysType;

    private String accUserType;

    private String accName;

    private String accIcon;

    private String accRemark;

    private Long accAmount;

    private Boolean accHasChild;

    private Boolean accCanPay;

    private Boolean accCanTransfer;

    private Integer accParentId;

    private Boolean isDel;
}
