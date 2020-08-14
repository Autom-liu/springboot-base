package com.edu.scnu.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * 	公共Vo对象之，分页数据存储的Vo对象
 * @author Administrator
 *
 * @param <T>
 */
@Data
public class PageVO<T> implements Serializable {

	private static final long serialVersionUID = -2207112935012444854L;

	private DefinitePage pageInfo;
	
	private List<T> data;
	
	public PageVO() {
		pageInfo = new DefinitePage();
	}
	
	public PageVO(Page<T> pageInfo) {
		this();
		this.pageInfo.currentPage = pageInfo.getNumber() + 1;
		this.pageInfo.pageSize = pageInfo.getSize();
		this.pageInfo.length = pageInfo.getNumberOfElements();
		this.pageInfo.total = pageInfo.getTotalElements();
		this.pageInfo.totalPage = pageInfo.getTotalPages();
		this.data = pageInfo.getContent();
		this.pageInfo.isFirst = pageInfo.isFirst();
		this.pageInfo.hasNext = pageInfo.hasNext();
	}
	
	public PageVO(Page<?> pageInfo, List<T> rows) {
		this();
		this.pageInfo.currentPage = pageInfo.getNumber() + 1;
		this.pageInfo.pageSize = pageInfo.getSize();
		this.pageInfo.length = pageInfo.getNumberOfElements();
		this.pageInfo.total = pageInfo.getTotalElements();
		this.pageInfo.totalPage = pageInfo.getTotalPages();
		this.data = rows;
		this.pageInfo.isFirst = pageInfo.isFirst();
		this.pageInfo.hasNext = pageInfo.hasNext();
	}
	
	@Data
	private class DefinitePage implements Serializable {

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
