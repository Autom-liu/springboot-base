package com.edu.scnu.idnt.service;

import java.util.List;

import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Role;
import com.edu.scnu.idnt.dto.RoleDTO;
import com.edu.scnu.idnt.query.RoleQuery;
import com.edu.scnu.idnt.vo.RoleVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface RoleService extends IService<Role, RoleDTO, RoleVO> {
	
	PageVO<RoleVO> queryPage(RoleQuery query);
	
	List<RoleVO> queryList(RoleQuery query);

	/**
	 * 创建角色
	 * @param roleDTO
	 * @return
	 */
    String createRole(RoleDTO roleDTO);
}
