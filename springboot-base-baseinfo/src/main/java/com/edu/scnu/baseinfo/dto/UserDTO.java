package com.edu.scnu.baseinfo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private String username;
    
    private String password;
    
    private String mobile;
    
    private String loginMobile;
    
    private String wechat;
    
    private String email;
    
    private String other;
    
    private Boolean sex;
    
    private String position;
    
    private Boolean status;
    
    private Date createTime;
    
    private Date updateTime;
    
}
