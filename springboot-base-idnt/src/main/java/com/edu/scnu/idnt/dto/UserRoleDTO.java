package com.edu.scnu.idnt.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserRoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    
    private String deptId;
    
    private String roleId;
    
    private Boolean isDefault;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
}
