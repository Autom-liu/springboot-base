package com.edu.scnu.common.query;

import java.io.Serializable;

import lombok.Data;

/**
 * 分页查询基础类
 * @author Autom
 *
 */
@Data
public class PageQuery implements Serializable {
	
	private static final long serialVersionUID = -3654116933868832414L;
	
	public static final Integer DEFAULT_CURRENTPAGE = 1;
	
	public static final Integer DEFAULT_PAGESIZE = 10;
	
	/**
	 * 当前页，默认为1
	 */
	private Integer currentPage = DEFAULT_CURRENTPAGE;
	
	/**
	 * 每页大小
	 */
	private Integer pageSize = DEFAULT_PAGESIZE;
	
	/**
	 * 是否需要分页，默认开启
	 */
	private Boolean pageFlag = true;
	
	/**
	 * 排序编号
	 */
	private Integer orderBy;
	
	/**
	 * 排序字段
	 */
	private String orderByField;
}
