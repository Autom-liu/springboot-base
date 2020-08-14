package com.edu.scnu.baseinfo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class DepartmentVO implements Serializable {
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
