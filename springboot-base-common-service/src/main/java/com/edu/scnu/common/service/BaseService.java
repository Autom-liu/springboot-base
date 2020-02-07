package com.edu.scnu.common.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.edu.scnu.common.base.BaseOrderByEnum;
import com.edu.scnu.common.bean.BaseExample;
import com.edu.scnu.common.query.PageQuery;
import com.edu.scnu.common.util.ConverterUtils;
import com.edu.scnu.common.vo.PageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 通用服务接口，它可以快速为我们完成增删改查（单个）的服务开发<br/>
 * 通过继承，即可实现单表查询零SQL开发
 * @author Autom
 *
 * @param <T> 实体类型
 * @param <DTO> DTO类型
 * @param <VO> VO类型
 */
public abstract class BaseService<T, DTO, VO> implements IService<T, DTO, VO> {
	
	@Autowired
	protected Mapper<T> mapper;
	
	private Class<T> beanClass;
	
	// private Class<DTO> dtoClass;
	
	private Class<VO> voClass;
	
	@SuppressWarnings("unchecked")
	protected BaseService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.beanClass = (Class<T>) pt.getActualTypeArguments()[0];
		// this.dtoClass = (Class<DTO>) pt.getActualTypeArguments()[1];
		this.voClass = (Class<VO>) pt.getActualTypeArguments()[2];
	}
	
	public List<VO> findAll() {
		List<T> all = mapper.selectAll();
		return ConverterUtils.copyList(all, voClass);
	}
	
	public List<VO> findByIds(List<Integer> ids) {
		List<T> result = mapper.selectByIdList(ids);
		
		return ConverterUtils.copyList(result, this.voClass);
	}
	
	public VO findOne(Integer id) {
		T bean = mapper.selectByPrimaryKey(id);
		VO result = ConverterUtils.copyBean(bean, this.voClass);
		return result;
	}
	
	public VO findOne(String id) {
		T bean = mapper.selectByPrimaryKey(id);
		VO result = ConverterUtils.copyBean(bean, this.voClass);
		return result;
	}
	
	public int insert(DTO dto) {
		T bean = ConverterUtils.copyBean(dto, this.beanClass);
		return mapper.insertSelective(bean);
	}
	
	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public int deleteByIds(List<Integer> idList) {
		String ids = StringUtils.join(idList, ',');
		return mapper.deleteByIds(ids);
	}
	
	public int updateByIdSelective(DTO dto) {
		T bean = ConverterUtils.copyBean(dto, beanClass);
		return mapper.updateByPrimaryKeySelective(bean);
	}
	
	/**
	 * 通用处理分页排序逻辑
	 * 可以通过：xxxExample.setOrderByClause(super.handlePageOrder(...)) 方便调用
	 * @see {@link handlePageOrder}
	 * @param query	PageQuery的条件查询对象
	 * @param clazz 排序枚举
	 * @return 在分页或不排序的情况下返回null，在不分页排序情况下返回需要排序的子句。
	 */
	protected final String handlePageOrder(PageQuery query, boolean needOrder) {
		Boolean hasOrder = (needOrder && query.getOrderBy() != null);
		Boolean pageFlag = query.getPageFlag();
		String orderField = null;
		if(hasOrder) {
			BaseOrderByEnum orderByEnum = query.getOrderBy();
			orderField = orderByEnum.getOrderField();
		}
		
		if(pageFlag && hasOrder) {
			// 分页排序，则使用PageHelper进行分页排序
			PageHelper.startPage(query.getCurrentPage(), query.getPageSize(), orderField);
		} else if (pageFlag && !hasOrder) {
			// 分页不排序
			PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		} else if (!pageFlag && hasOrder) {
			// 不分页要排序
		} else {
			// 不分页也不排序
		}
		return orderField;
	}
	
	/**
	 * 通用处理分页排序逻辑，自动构造分页排序字段
	 * @param query	PageQuery的条件查询对象
	 * @param clazz 排序枚举
	 * @param example 查询条件对象
	 */
	protected final void handlePageOrder(PageQuery query, boolean needOrder, BaseExample example) {
		example.setOrderByClause(handlePageOrder(query, needOrder));
	}
	
	/**
	 * 分页查询结果集处理，调用它可以很方便将处理的分页结果集转换VO对象
	 * @param result
	 * @return
	 */
	protected final PageVO<VO> handlePageResult(List<T> result) {
		PageInfo<T> pageInfo = new PageInfo<T>(result);
		
		List<VO> list = ConverterUtils.copyList(pageInfo.getList(), this.voClass);
		
		return new PageVO<>(pageInfo, list);
	}

}
