package com.edu.scnu.admin.mapper;

import com.edu.scnu.common.service.Mapper;
import com.edu.scnu.admin.bean.Permission;
import com.edu.scnu.admin.bean.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface PermissionMapper extends BaseMapper<Permission> {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    List<Permission> selectByExample(PermissionExample example);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);
}