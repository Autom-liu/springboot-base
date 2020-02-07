package com.edu.scnu.common.service;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.edu.scnu.common.bean.BaseExample;
import com.edu.scnu.common.enums.BaseOrderByEnum;
import com.edu.scnu.common.enums.CommonBizEnum;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.query.PageQuery;
import com.edu.scnu.common.util.DataUtils;
import com.edu.scnu.common.vo.PageVO;

public abstract class BaseService<T, DTO, VO> implements IService<T, DTO, VO> {
	
	@Autowired
	protected Mapper<T> mapper;
	
	private Class<T> beanClass;
	
	private Class<DTO> dtoClass;
	
	private Class<VO> voClass;
	
	@SuppressWarnings("unchecked")
	protected BaseService() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.beanClass = (Class<T>) pt.getActualTypeArguments()[0];
		this.dtoClass = (Class<DTO>) pt.getActualTypeArguments()[1];
		this.voClass = (Class<VO>) pt.getActualTypeArguments()[2];
	}
	
	public List<VO> findAll() {
		List<T> all = mapper.selectAll();
		
		return DataUtils.copyList(all, this.voClass, (r, t) -> fieldConverter(r, t));
	}
	
	public List<VO> findByIds(List<Integer> ids) {
		List<T> result = mapper.selectByIdList(ids);
		
		return DataUtils.copyList(result, this.voClass, (r, t) -> fieldConverter(r, t));
	}
	
	public VO findOne(Integer id) {
		T bean = mapper.selectByPrimaryKey(id);
		VO result = DataUtils.copyBean(bean, this.voClass);
		this.fieldConverter(bean, result);
		return result;
	}
	
	public int insert(DTO dto) {
		T bean = DataUtils.copyBean(dto, this.beanClass);
		return mapper.insertSelective(bean);
	}
	
	public int deleteById(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	
	public void deleteByIds(List<Integer> idsList) {
		String ids = StringUtils.join(idsList, ",");
		mapper.deleteByIds(ids);
	}
	
	/**
	 * 通用处理分页排序逻辑
	 * 可以通过：xxxExample.setOrderByClause(super.handlePageOrder(...)) 方便调用
	 * @param query	PageQuery的条件查询对象
	 * @param clazz 排序枚举
	 * @return 在分页或不排序的情况下返回null，在不分页排序情况下返回需要排序的子句。
	 */
	protected final String handlePageOrder(PageQuery query, Class<? extends BaseOrderByEnum> clazz) {
		Boolean hasOrder = (clazz != null && query.getOrderBy() != null);
		Boolean pageFlag = query.getPageFlag();
		query.setOrderByField(null);
		String orderField = null;
		if(hasOrder) {
			
			orderField = DataUtils.getEnumByCode(query.getOrderBy(), clazz).getOrderField();
			
			if (orderField == null) {
				throw new BizException(CommonBizEnum.BAD_ORDER_FIELD);
			}
		}
		
		if(pageFlag && hasOrder) {
			// 分页排序，则使用PageHelper进行分页排序
			PageHelper.startPage(query.getCurrentPage(), query.getPageSize(), orderField);
		} else if (pageFlag && !hasOrder) {
			// 分页不排序
			PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		} else if (!pageFlag && hasOrder) {
			// 不分页要排序
			query.setOrderByField(orderField);
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
	protected final void handlePageOrder(PageQuery query, Class<? extends BaseOrderByEnum> clazz, BaseExample example) {
		example.setOrderByClause(handlePageOrder(query, clazz));
	}
	
	protected PageVO<VO> handlePageResult(List<T> result) {
		PageInfo<T> pageInfo = new PageInfo<T>(result);
		
		List<VO> list = DataUtils.copyList(pageInfo.getList(), this.voClass, (r, t) -> fieldConverter(r, t));
		
		return new PageVO<>(pageInfo, list);
	}
	
	/**
	 * hook 钩子函数，用于字段格式转换，子类可重写之
	 * @param source
	 * @param target
	 */
	protected void fieldConverter(T source, VO target) {
		
	}
}
