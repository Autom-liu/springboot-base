package com.edu.scnu.common.vo;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;

import lombok.Data;

/**
 * 	公共Vo对象之，分页数据存储的Vo对象
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class PageVO<T> implements Serializable {

	private static final long serialVersionUID = -2207112935012444854L;

	private Page pageInfo;
	
	private List<T> data;
	
	public PageVO() {
		pageInfo = new Page();
	}
	
	public PageVO(PageInfo<T> pageInfo) {
		this();
		this.pageInfo.currentPage = pageInfo.getPageNum();
		this.pageInfo.pageSize = pageInfo.getPageSize();
		this.pageInfo.length = pageInfo.getSize();
		this.pageInfo.total = pageInfo.getTotal();
		this.pageInfo.totalPage = pageInfo.getPages();
		this.data = pageInfo.getList();
		this.pageInfo.isFirst = pageInfo.isIsFirstPage();
		this.pageInfo.hasNext = pageInfo.isHasNextPage();
	}
	
	public PageVO(PageInfo<?> pageInfo, List<T> rows) {
		this();
		this.pageInfo.currentPage = pageInfo.getPageNum();
		this.pageInfo.pageSize = pageInfo.getPageSize();
		this.pageInfo.length = pageInfo.getSize();
		this.pageInfo.total = pageInfo.getTotal();
		this.pageInfo.totalPage = pageInfo.getPages();
		this.data = rows;
		this.pageInfo.isFirst = pageInfo.isIsFirstPage();
		this.pageInfo.hasNext = pageInfo.isHasNextPage();
	}
	
	@Data
	private class Page implements Serializable {

		private static final long serialVersionUID = -4019585481529601742L;

		private Integer currentPage;
		/** 每页条数 **/
		private Integer pageSize;
		/** 当前页的条数 **/
		private Integer length;
		/** 总记录数 **/
		private Long total;
		/** 总页数 **/
		private Integer totalPage;
		
		private Boolean isFirst;
		
		private Boolean hasNext;
	}
	
}