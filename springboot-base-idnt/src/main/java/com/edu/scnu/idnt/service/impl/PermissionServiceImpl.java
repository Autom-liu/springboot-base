package com.edu.scnu.idnt.service.impl;

import java.util.Arrays;
import java.util.List;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.idnt.service.*;
import com.edu.scnu.idnt.vo.MenuVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Permission;
import com.edu.scnu.idnt.bean.PermissionExample;
import com.edu.scnu.idnt.dto.PermissionDTO;
import com.edu.scnu.idnt.mapper.PermissionMapper;
import com.edu.scnu.idnt.query.PermissionQuery;
import com.edu.scnu.idnt.vo.PermissionVO;
import tk.mybatis.mapper.common.BaseMapper;

@Service
@Slf4j
public class PermissionServiceImpl extends CommonService<Permission, PermissionDTO, PermissionVO> implements PermissionService {

    private PermissionMapper permissionMapper;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

	public PermissionServiceImpl(PermissionMapper permissionMapper) {
		super(Permission.class, PermissionDTO.class, PermissionVO.class, permissionMapper);
		this.permissionMapper = permissionMapper;
	}

	@Override
	public PageVO<PermissionVO> queryPage(PermissionQuery query) {
		
		PermissionExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<Permission> resultList = permissionMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<PermissionVO> queryList(PermissionQuery query) {
		query.setPageFlag(false);
		PageVO<PermissionVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	@Override
	public IResult assignPermission(String userId, String deptId, String roleId, String[] menuIds) throws BizException {

		if (StringUtils.isNotEmpty(userId) && !userService.isExist(userId)) {
			return IResult.error("10002", "用户信息不存在");
		}

		if (StringUtils.isNotEmpty(deptId) && !departmentService.isExist(deptId)) {
			return IResult.error("10003", "机构信息不存在");
		}

		if (StringUtils.isNotEmpty(roleId) && !roleService.isExist(roleId)) {
			return IResult.error("10004", "角色信息不存在");
		}

		int menuCount = menuService.countByIds(menuIds);
		if (menuCount != menuIds.length) {
			return IResult.error("10008", "菜单信息不存在");
		}

		userId = StringUtils.defaultIfBlank(userId, "0");
		deptId = StringUtils.defaultString(deptId, "0");
		roleId = StringUtils.defaultString(roleId, "0");
		for (String menuId : menuIds) {
			Permission permission = new Permission();
			permission.setDeptId(deptId);
			permission.setUserId(userId);
			permission.setRoleId(roleId);
			permission.setMenuId(menuId);
			try {
				permissionMapper.insertSelective(permission);
			} catch (DuplicateKeyException e) {
				// ignore duplicate
				log.warn("ignore duplicate: {} - {}", permission, e.getMessage());
			}
		}
		return Result.success("success");
	}

	private class ExampleProxy extends PermissionExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
