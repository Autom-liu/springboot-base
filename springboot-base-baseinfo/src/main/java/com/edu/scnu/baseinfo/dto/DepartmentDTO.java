package com.edu.scnu.baseinfo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DepartmentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Integer deptNo;
    
    private String deptName;
    
    private String deptType;
    
    private Integer parentId;
    
    private Integer parentNo;
    
    private String applist;
    
    private Date createTime;
    
    private Date updateTime;
    
}
