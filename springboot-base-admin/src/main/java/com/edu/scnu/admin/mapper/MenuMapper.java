package com.edu.scnu.admin.mapper;

import com.edu.scnu.common.service.Mapper;
import com.edu.scnu.admin.bean.Menu;
import com.edu.scnu.admin.bean.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper extends Mapper<Menu> {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    List<Menu> selectByExample(MenuExample example);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);
}