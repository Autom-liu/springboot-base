package com.edu.scnu.idnt.web.api;

import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.idnt.dto.RoleDTO;
import com.edu.scnu.idnt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("role")
public class RoleAPI {

    @Autowired
    private RoleService roleService;

    @PostMapping("create")
    public IResult create(@Valid RoleDTO roleDTO) {
        roleDTO.setRoleId(null);
        String roleId = roleService.createRole(roleDTO);
        return Result.success(roleId);
    }

}
