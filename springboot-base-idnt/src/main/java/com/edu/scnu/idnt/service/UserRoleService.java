package com.edu.scnu.idnt.service;

import java.util.List;

import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.UserRole;
import com.edu.scnu.idnt.dto.UserRoleDTO;
import com.edu.scnu.idnt.query.UserRoleQuery;
import com.edu.scnu.idnt.vo.UserRoleVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface UserRoleService extends IService<UserRole, UserRoleDTO, UserRoleVO> {
	
	PageVO<UserRoleVO> queryPage(UserRoleQuery query);
	
	List<UserRoleVO> queryList(UserRoleQuery query);
	
}
