package com.edu.scnu.admin.web.api;

import com.edu.scnu.admin.bean.Department;
import com.edu.scnu.admin.bean.User;
import com.edu.scnu.admin.bean.UserRole;
import com.edu.scnu.admin.bean.UserRoleExample;
import com.edu.scnu.admin.dto.DepartmentDTO;
import com.edu.scnu.admin.dto.UserDTO;
import com.edu.scnu.admin.enums.ErrorEnum;
import com.edu.scnu.admin.mapper.DepartmentMapper;
import com.edu.scnu.admin.mapper.RoleMapper;
import com.edu.scnu.admin.mapper.UserMapper;
import com.edu.scnu.admin.mapper.UserRoleMapper;
import com.edu.scnu.admin.vo.DeptRoleEntity;
import com.edu.scnu.admin.vo.UserEntity;
import com.edu.scnu.admin.web.config.CurrentUser;
import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.util.ConverterUtils;
import com.edu.scnu.common.util.StringUtils;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 本地管理的用户机构管理接口
 */
@Slf4j
@ResponseBody
public class LocalDeptUserAPI {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 首次创建用户接口
     * @param userDTO
     * @return
     */
    @PostMapping("user/create")
    @Transactional(rollbackFor = Exception.class)
    public IResult createUser(UserDTO userDTO) throws BizException {
        userDTO.setUserId(null);
        userDTO.setStatus(1);
        if (StringUtils.isEmpty(userDTO.getDeptId()) && StringUtils.isEmpty(userDTO.getRoleId())) {
            return IResult.error(ErrorEnum.ERRCODE_0001);
        }
        User user = ConverterUtils.copyBean(userDTO, User.class);
        String userId = RandomStringUtils.randomNumeric(9);
        String password = RandomStringUtils.randomAlphabetic(8);
        user.setUserId(userId);
        user.setPassword(password);

        userMapper.insertSelective(user);
        return assignDeptRole(userId, userDTO.getDeptId(), userDTO.getRoleId(), true);
    }


    /**
     * 用户权限分配接口
     * @param userId
     * @param deptId
     * @param roleId
     * @param isDefault
     * @return
     */
    @PostMapping("user/assign")
    @Transactional(rollbackFor = Exception.class)
    public IResult assignDeptRole(String userId, String deptId, String roleId, Boolean isDefault) throws BizException {
        if (StringUtils.isEmpty(userId)) {
            throw new BizException(ErrorEnum.ERRCODE_0002);
        }
        isDefault = isDefault == null ? false : isDefault;
        if (!userMapper.existsWithPrimaryKey(userId)) {
            throw new BizException(ErrorEnum.ERRCODE_0002);
        }
        if (StringUtils.isNotEmpty(deptId) && !departmentMapper.existsWithPrimaryKey(deptId)) {
            throw new BizException(ErrorEnum.ERRCODE_0003);
        }
        if (StringUtils.isNotEmpty(roleId) && !roleMapper.existsWithPrimaryKey(roleId)) {
            throw new BizException(ErrorEnum.ERRCODE_0004);
        }

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        if (isDefault) {
            userRole.setIsDefault(false);
            UserRoleExample example = new UserRoleExample();
            example.createCriteria().andUserIdEqualTo(userId).andIsDefaultEqualTo(true);
            userRoleMapper.updateByExample(userRole, example);
        }
        userRole.setDeptId(StringUtils.defaultString(deptId, "0"));
        userRole.setRoleId(StringUtils.defaultString(roleId, "0"));
        userRole.setIsDefault(isDefault);
        userRole.setStatus(0);

        try {
            userRoleMapper.insertSelective(userRole);
        } catch (DuplicateKeyException e) {
            log.warn("userRoleMapper.insertSelective 插入失败： {} - {}", userRole, e.getMessage());
            throw new BizException(ErrorEnum.ERRCODE_0005);
        }
        return Result.success("success");
    }

    @PostMapping("user/changePasswd")
    public IResult changePasswd(String userId, String oldPasswd, String newPasswd, @CurrentUser UserEntity userEntity) {
        User current = userEntity.getUser();
        if (!current.getUserId().equals(userId)) {
            return IResult.error(ErrorEnum.ERRCODE_0010);
        }

        User user = new User();
        user.setUserId(userId);
        User validation = userMapper.selectOne(user);
        if (!validation.getPassword().equals(oldPasswd)) {
            return IResult.error(ErrorEnum.ERRCODE_0006);
        }
        user.setPassword(newPasswd);
        user.setStatus(0);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success("修改成功");
    }

    @PostMapping("user/resetPasswd")
    public IResult resetPasswd(String userId) {
        String password = RandomStringUtils.randomAlphabetic(8);
        User user = new User();
        user.setUserId(userId);
        user.setPassword(password);
        user.setStatus(1);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success("重置成功");
    }

    @PostMapping("user/login")
    public IResult userLogin(String userId, String password, HttpSession session) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return IResult.error(ErrorEnum.ERRCODE_0007);
        }
        session.setAttribute("USER_ID", userId);
        IResult getRes = getCurrentUser(null, session);
        if (getRes.isSuccess()) {
            Result<UserEntity> userResult = (Result<UserEntity>) getRes;
            UserEntity userEntity = userResult.getData();
            session.setAttribute("CURRENT_USER", userEntity);
            return Result.success(session.getId());
        } else {
            return getRes;
        }
    }

    @PostMapping("user/current")
    public IResult getCurrentUser(@CurrentUser UserEntity userEntity, HttpSession session) {
        if (userEntity == null) {
            String userId = StringUtils.toString(session.getAttribute("USER_ID"));
            if (StringUtils.isEmpty(userId)) {
                return IResult.error(ErrorEnum.ERRCODE_0008);
            }
            userEntity = new UserEntity();
            User user = userMapper.selectByPrimaryKey(userId);
            userEntity.setUser(user);
            List<DeptRoleEntity> dpList = getDeptRole(userId);
            if (CollectionUtils.isEmpty(dpList)) {
                return IResult.error(ErrorEnum.ERRCODE_0009);
            }
            userEntity.setDeptRoleList(dpList);
            DeptRoleEntity defaultDp = selectDefault(dpList);
            userEntity.setCurrentDept(defaultDp.getDepartment());
            userEntity.setCurrentRole(defaultDp.getRole());

        }
        return Result.success(userEntity);
    }

    private List<DeptRoleEntity> getDeptRole(String userId) {
        List<DeptRoleEntity> deptRoleEntity = userRoleMapper.joinWithDeptRole(userId);
        return deptRoleEntity;
    }

    private DeptRoleEntity selectDefault(List<DeptRoleEntity> dpList) {
        if (CollectionUtils.isEmpty(dpList)) {
            return null;
        }
        for (DeptRoleEntity deptRoleEntity : dpList) {
            if (deptRoleEntity.getIsDefault()) {
                return deptRoleEntity;
            }
        }
        return dpList.get(0);
    }

    @PostMapping("dept/create")
    public IResult createDept(DepartmentDTO departmentDTO) {
        Department department = ConverterUtils.copyBean(departmentDTO, Department.class);
        String parentId = departmentDTO.getParentId();
        if ("0".equals(parentId)) {
            department.setDeptLevel(1);
        } else {
            Department pdq = new Department();
            pdq.setDeptId(parentId);
            Department pd = departmentMapper.selectOne(pdq);
            department.setDeptLevel(pd.getDeptLevel() + 1);
        }

        String deptId = RandomStringUtils.randomNumeric(6);
        department.setDeptId(deptId);
        departmentMapper.insertSelective(department);
        return Result.success(department);
    }

}
