package com.edu.scnu.idnt.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DepartmentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deptId;
    
    private String deptName;
    
    private String parentId;
    
    private Integer deptLevel;
    
    private Boolean deptType;
    
    private String deptManager;
    
    private String deptCategory;
    
    private String deptProperty;
    
    private String phone;
    
    private String address;
    
    private Date effectiveTime;
    
    private String remark;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
}
