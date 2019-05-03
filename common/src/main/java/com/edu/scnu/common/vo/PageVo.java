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
public class PageVo<T> implements Serializable {

	private static final long serialVersionUID = -2207112935012444854L;

	private Page pageInfo;
	
	private List<T> data;
	
	public PageVo() {
		pageInfo = new Page();
	}
	
	public PageVo(PageInfo<T> pageInfo) {
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
	
	public PageVo(PageInfo<?> pageInfo, List<T> rows) {
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
		
		private Integer pageSize;
		
		private Integer length;
		
		private Long total;
		
		private Integer totalPage;
		
		private Boolean isFirst;
		
		private Boolean hasNext;
	}
	
}
