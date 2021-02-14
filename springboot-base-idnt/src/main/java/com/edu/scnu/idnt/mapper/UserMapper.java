package com.edu.scnu.idnt.mapper;

import com.edu.scnu.common.service.Mapper;
import com.edu.scnu.idnt.bean.User;
import com.edu.scnu.idnt.bean.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends Mapper<User> {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    List<User> selectByExample(UserExample example);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);
}