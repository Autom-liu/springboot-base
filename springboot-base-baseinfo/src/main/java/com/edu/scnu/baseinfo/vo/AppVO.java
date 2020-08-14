package com.edu.scnu.baseinfo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class AppVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String simpleName;
    
    private String fullName;
    
    private String description;
    
    private String manager;
    
    private String managerMobile;
    
    private String domain;
    
    private String company;
    
    private Date createTime;
    
    private Date updateTime;
    
}
