package com.edu.scnu.idnt.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class MenuVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String menuId;
    
    private String menuName;
    
    private String parentId;
    
    private Integer menuType;
    
    private String url;
    
    private Integer order;
    
    private String remark;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Boolean status;
    
}