package com.edu.scnu.common.base;

import com.edu.scnu.common.query.QueryBuilder;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.ids.DeleteByIdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

public interface BaseMapper<T> extends tk.mybatis.mapper.common.BaseMapper<T>,
        SelectByIdListMapper<T, Integer>,
        InsertListMapper<T>,
        DeleteByIdsMapper<T> {

    long countByExample(QueryBuilder<T> example);

    int deleteByExample(QueryBuilder<T> example);

    List<T> selectByExample(QueryBuilder<T> builder);

}
