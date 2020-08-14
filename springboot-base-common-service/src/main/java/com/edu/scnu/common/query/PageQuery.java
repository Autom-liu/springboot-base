package com.edu.scnu.common.query;

import java.io.Serializable;

import com.edu.scnu.common.base.BaseOrderByEnum;

import lombok.Data;

/**
 * 分页查询基础类
 * @author Autom
 * @date 2020年2月4日
 * @version 1.0
 *
 */
@Data
public class PageQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer DEFAULT_CURRENTPAGE = 1;
	
	public static final Integer DEFAULT_PAGESIZE = 10;
	
	/**
	 * 当前页，默认为1
	 */
	private Integer currentPage;
	
	/**
	 * 每页大小
	 */
	private Integer pageSize;
	
	/**
	 * 是否需要分页，默认开启
	 */
	private Boolean pageFlag;
	
	/**
	 * 排序枚举
	 */
	private BaseOrderByEnum orderBy;

}
