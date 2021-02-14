package com.edu.scnu.idnt.web.api;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.idnt.dto.UserDTO;
import com.edu.scnu.idnt.service.UserService;
import com.edu.scnu.idnt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public IResult createUser(@Valid UserDTO userDTO) throws BizException {
        userDTO.setUserId(null);
        String deptId = userDTO.getDeptId();
        String roleId = userDTO.getRoleId();
        if (StringUtils.isEmpty(deptId) && StringUtils.isEmpty(roleId)) {
            return IResult.error("10006", "初建用户请分配");
        }
        String userId = userService.createUser(userDTO);
        return Result.success(userId);
    }

    @PostMapping("changePassword")
    public IResult changePassword(String userId, String oldPassword, String newPassword) {
        return userService.changePassword(userId, oldPassword, newPassword);
    }

    @PostMapping("resetPassword")
    public IResult resetPassword(String userId) {
        return userService.resetPassword(userId);
    }

    @PostMapping("update")
    public IResult updateUser(UserDTO userDTO) throws BizException {
        userService.updateByIdSelective(userDTO);
        return Result.success("修改成功");
    }

    @PostMapping("assign")
    public IResult assignDeptRole(String userId, String roleId, String deptId, UserVO user) throws BizException {
        log.info("current User: {}", user);
        // TODO 参数校验
        if (StringUtils.isEmpty(userId)) {
            return IResult.error("401", "用户编号不能为空");
        }
        return userService.assignDeptRole(userId, deptId, roleId, false);
    }

    @PostMapping("login")
    public IResult login(String userId, String password, HttpSession session) throws BizException {
        UserVO userVO = userService.findOne(userId);
        if (userVO == null || !userVO.getPassword().equals(password)) {
            return IResult.error("40015", "帐号密码错误");
        }
        session.setAttribute("CURRENT_USER", userVO);
        return Result.success(session.getId());
    }

}
