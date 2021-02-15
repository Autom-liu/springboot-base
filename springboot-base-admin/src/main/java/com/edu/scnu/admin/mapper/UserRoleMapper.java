package com.edu.scnu.admin.mapper;

import com.edu.scnu.admin.vo.DeptRoleEntity;
import com.edu.scnu.admin.bean.UserRole;
import com.edu.scnu.admin.bean.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    List<UserRole> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    List<DeptRoleEntity> joinWithDeptRole(String userId);

}