package com.edu.scnu.user.query.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageQuery implements Serializable {

	private static final long serialVersionUID = 5003706671102967289L;

	/**
	 * 当前页
	 */
	private Integer currentPage;
	
	/**
	 * 每页数量
	 */
	private Integer pageSize;
	
	/**
	 * 是否开启分页
	 */
	private Boolean pageFlag = false;
	
	/**
	 * 排序字段编号
	 */
	private Integer orderBy;
	
	/**
	 * 排序数据库表字段
	 */
	private String orderByFiled;
	
}
