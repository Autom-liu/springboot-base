package com.edu.scnu.common.bean;

/**
 * If you want to handle pagination and order by clause by using
 * com.edu.scnu.common.service.BaseService.handlePageOrder(PageQuery, Class<? extends BaseOrderByEnum>, BaseExample)
 * you should make your xxxExample bean implements BaseExample
 * @author Autom
 *
 */
public abstract class BaseExample {
	
	public abstract void setOrderByClause(String orderByClause);
	
	public abstract String getOrderByClause();
	
	public abstract void setDistinct(boolean distinct);
	
	public abstract boolean isDistinct();
	
}
