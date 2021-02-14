package com.edu.scnu.common.service;

import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface Mapper<T> extends
							BaseMapper<T>,
							IdsMapper<T>,
							InsertListMapper<T> {

}
