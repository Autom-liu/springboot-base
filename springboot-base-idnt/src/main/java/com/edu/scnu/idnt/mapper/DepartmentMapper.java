package com.edu.scnu.idnt.mapper;

import com.edu.scnu.common.service.Mapper;
import com.edu.scnu.idnt.bean.Department;
import com.edu.scnu.idnt.bean.DepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper extends Mapper<Department> {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    List<Department> selectByExample(DepartmentExample example);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);
}