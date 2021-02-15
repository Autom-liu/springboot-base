package com.edu.scnu.admin.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    
    private String deptId;
    
    private String roleId;
    
    private String menuId;
    
}
