package com.edu.scnu.idnt.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String deptId;

    @NotNull(message = "用户名称不能为空")
    private String deptName;

    @NotNull(message = "父机构编号不能为空")
    private String parentId;
    
    private Integer deptType;
    
    private String deptManager;
    
    private String deptCategory;
    
    private String deptProperty;
    
    private String phone;
    
    private String address;
    
    private Date effectiveTime;
    
    private String remark;
    
    private Integer status;
    
}
