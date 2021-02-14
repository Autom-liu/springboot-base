package com.edu.scnu.idnt.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MenuDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "菜单编号不能为空")
    private String menuId;

    @NotNull(message = "菜单名称不能为空")
    private String menuName;

    @NotNull(message = "父级菜单不能为空")
    private String parentId;
    
    private Integer menuType;

    @NotNull(message = "菜单地址不能为空")
    private String url;
    
    private Integer order;
    
    private String remark;
    
}
