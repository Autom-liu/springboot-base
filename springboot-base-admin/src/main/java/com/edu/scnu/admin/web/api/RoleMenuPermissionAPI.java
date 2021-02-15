package com.edu.scnu.admin.web.api;

import com.edu.scnu.admin.bean.Department;
import com.edu.scnu.admin.bean.Menu;
import com.edu.scnu.admin.bean.Permission;
import com.edu.scnu.admin.bean.Role;
import com.edu.scnu.admin.bean.User;
import com.edu.scnu.admin.bean.UserRole;
import com.edu.scnu.admin.bean.UserRoleExample;
import com.edu.scnu.admin.dto.MenuDTO;
import com.edu.scnu.admin.dto.RoleDTO;
import com.edu.scnu.admin.enums.ErrorEnum;
import com.edu.scnu.admin.mapper.DepartmentMapper;
import com.edu.scnu.admin.mapper.MenuMapper;
import com.edu.scnu.admin.mapper.PermissionMapper;
import com.edu.scnu.admin.mapper.RoleMapper;
import com.edu.scnu.admin.mapper.UserMapper;
import com.edu.scnu.admin.mapper.UserRoleMapper;
import com.edu.scnu.admin.vo.UserEntity;
import com.edu.scnu.admin.web.config.CurrentUser;
import com.edu.scnu.admin.web.config.IgnorePermission;
import com.edu.scnu.common.enums.DefaultSysErrorEnum;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.util.ConverterUtils;
import com.edu.scnu.common.util.StringUtils;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.common.vo.ResultList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单权限管理接口，获取用户权限信息
 */
@Slf4j
@ResponseBody
@RequestMapping("admin")
public class RoleMenuPermissionAPI {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @PostMapping("role/create")
    public IResult createRole(RoleDTO roleDTO) {
        Role role = ConverterUtils.copyBean(roleDTO, Role.class);
        roleMapper.insertSelective(role);
        return Result.success("success");
    }

    @PostMapping("menu/create")
    public IResult createMenu(MenuDTO menuDTO) {
        Menu menu = ConverterUtils.copyBean(menuDTO, Menu.class);
        menuMapper.insertSelective(menu);
        return Result.success("success");
    }

    /**
     * 用户分配接口
     * @param userId
     * @param deptId
     * @param roleId
     * @param isDefault
     * @return
     */
    @PostMapping("role/assign")
    public IResult assignDeptRole(String userId, String deptId, String roleId, Boolean isDefault) {
        return IResult.error(DefaultSysErrorEnum.INTERFACE_NOT_FOUND, "暂不支持");
    }

    @PostMapping("permission/assign")
    @Transactional
    public IResult assignPermission(String userId, String deptId, String roleId, String[] menuIds) {

        if (StringUtils.isNotEmpty(userId) && !userMapper.existsWithPrimaryKey(userId)) {
            return IResult.error(ErrorEnum.ERRCODE_0002);
        }

        if (StringUtils.isNotEmpty(deptId) && !departmentMapper.existsWithPrimaryKey(deptId)) {
            return IResult.error(ErrorEnum.ERRCODE_0003);
        }

        if (StringUtils.isNotEmpty(roleId) && !roleMapper.existsWithPrimaryKey(roleId)) {
            return IResult.error(ErrorEnum.ERRCODE_0004);
        }

        String ids = StringUtils.join(menuIds, ',');
        List<Menu> menuList = menuMapper.selectByIds(ids);
        if (menuList.size() != menuIds.length) {
            return IResult.error(ErrorEnum.ERRCODE_0011);
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

    @IgnorePermission(permissionCheck = false)
    @PostMapping("permission/current")
    public IResult fetchPermission(@CurrentUser UserEntity userEntity, HttpSession session) {

        User user = userEntity.getUser();
        String userId = user.getUserId();

        Department currentDept = userEntity.getCurrentDept();
        Role currentRole = userEntity.getCurrentRole();
        String deptId = currentDept.getDeptId();
        String roleId = currentRole.getRoleId();
        Permission query = new Permission();
        query.setUserId(userId);
        query.setDeptId(deptId);
        query.setRoleId(roleId);
        List<Menu> menuList = permissionMapper.joinWithMenu(query);
        if (CollectionUtils.isEmpty(menuList)) {
            query.setUserId("0");
            menuList = permissionMapper.joinWithMenu(query);
        }

        List<String> permissionList = menuList.stream().map(Menu::getUrl).collect(Collectors.toList());

        session.setAttribute("CURRENT_PERMISSION", permissionList);

        return ResultList.success(permissionList);
    }



}
