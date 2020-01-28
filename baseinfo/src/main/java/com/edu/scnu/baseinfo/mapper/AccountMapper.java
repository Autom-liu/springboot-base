package com.edu.scnu.baseinfo.mapper;

import com.edu.scnu.baseinfo.bean.Account;
import com.edu.scnu.baseinfo.bean.AccountExample;
import com.edu.scnu.common.service.Mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper extends Mapper<Account> {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    List<Account> selectByExample(AccountExample example);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);
}