package com.edu.scnu.baseinfo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class AppDTO implements Serializable {
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
