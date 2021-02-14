package com.edu.scnu.idnt.service;

import java.util.List;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Permission;
import com.edu.scnu.idnt.dto.PermissionDTO;
import com.edu.scnu.idnt.query.PermissionQuery;
import com.edu.scnu.idnt.vo.PermissionVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface PermissionService extends IService<Permission, PermissionDTO, PermissionVO> {
	
	PageVO<PermissionVO> queryPage(PermissionQuery query);
	
	List<PermissionVO> queryList(PermissionQuery query);

	/**
	 * 权限分配
	 * @param userId
	 * @param deptId
	 * @param roleId
	 * @param menuIds
	 * @return
	 * @throws BizException
	 */
	IResult assignPermission(String userId, String deptId, String roleId, String[] menuIds) throws BizException;
	
}
