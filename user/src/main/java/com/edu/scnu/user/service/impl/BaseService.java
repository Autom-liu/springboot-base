package com.edu.scnu.user.service.impl;

import com.edu.scnu.common.enums.BaseOrderByEnum;
import com.edu.scnu.common.enums.ErrorCode;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.utils.DataUtils;
import com.edu.scnu.user.query.common.PageQuery;
import com.github.pagehelper.PageHelper;

public class BaseService {

	/**
	 * 处理分页和排序的相关逻辑
	 * 		最终会设置query.orderByFiled字段
	 * 		如果为空，则代表不需要排序
	 * @param query 分页排序的查询条件
	 */
	protected void handlePageOrder(PageQuery query, Class<? extends BaseOrderByEnum> clazz) {
		Boolean hasOrder = query.getOrderBy() != null;
		query.setOrderByFiled(null);
		String orderField = null;
		if(hasOrder) {
			
			orderField = DataUtils.getEnumByCode(query.getOrderBy(), clazz).getOrderFiled();
			
			if (orderField == null) {
				throw new BizException(ErrorCode.BadOrderByField);
			}
		}
		
		if(query.getPageFlag() && hasOrder) {
			// 分页排序，则使用PageHelper进行分页排序
			PageHelper.startPage(query.getCurrentPage(), query.getPageSize(), orderField);
		} else if (query.getPageFlag() && !hasOrder) {
			// 分页不排序
			PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
		} else if (!query.getPageFlag() && hasOrder) {
			// 不分页要排序
			query.setOrderByFiled(orderField);
		} else {
			// 不分页也不排序
		}

	}
	
}
