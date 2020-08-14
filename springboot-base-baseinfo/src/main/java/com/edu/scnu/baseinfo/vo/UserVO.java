package com.edu.scnu.baseinfo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserVO implements Serializable {
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
