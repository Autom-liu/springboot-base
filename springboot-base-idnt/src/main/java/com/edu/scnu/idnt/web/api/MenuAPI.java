package com.edu.scnu.idnt.web.api;

import com.edu.scnu.common.exception.BizException;
import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.idnt.dto.MenuDTO;
import com.edu.scnu.idnt.service.MenuService;
import com.edu.scnu.idnt.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("menu")
public class MenuAPI {

    @Autowired
    private MenuService menuService;

    @Autowired
    private PermissionService permissionService;

    @PostMapping("create")
    public IResult create(@Valid MenuDTO menuDTO) throws BizException {
        menuService.insert(menuDTO);
        return Result.success("success");
    }

    @PostMapping("assign")
    public IResult assignPermission(String userId, String deptId, String roleId, String[] menuIds) throws BizException {
        if (StringUtils.isEmpty(userId) && StringUtils.isEmpty(deptId) && StringUtils.isEmpty(roleId)) {
            return IResult.error("10007", "请输入合法分配对象");
        }

        if (StringUtils.isNotEmpty(userId) && StringUtils.isEmpty(deptId) && StringUtils.isEmpty(roleId)) {
            return IResult.error("10007", "请输入合法分配对象");
        }

        if (menuIds == null || menuIds.length == 0) {
            return IResult.error("10008", "无权限列表");
        }


        return permissionService.assignPermission(userId, deptId, roleId, menuIds);
    }

}
