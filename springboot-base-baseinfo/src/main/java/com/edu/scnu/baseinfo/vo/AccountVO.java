package com.edu.scnu.baseinfo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AccountVO implements Serializable {
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
