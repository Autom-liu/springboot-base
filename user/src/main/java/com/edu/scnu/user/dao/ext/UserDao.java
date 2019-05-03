package com.edu.scnu.user.dao.ext;

import org.apache.ibatis.annotations.Mapper;

import com.edu.scnu.user.bean.User;
import com.edu.scnu.user.dao.UserMapper;

@Mapper
public interface UserDao extends UserMapper {
	
	void saveOrUpdate(User user);

}
