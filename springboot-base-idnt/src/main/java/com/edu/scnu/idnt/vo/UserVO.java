package com.edu.scnu.idnt.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;
    
    private String userName;
    
    private String password;
    
    private String mobile;
    
    private String officePhone;
    
    private String email;
    
    private String remark;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
}
