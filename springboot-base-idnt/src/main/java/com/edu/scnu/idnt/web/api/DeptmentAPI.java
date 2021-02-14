package com.edu.scnu.idnt.web.api;

import com.edu.scnu.common.vo.IResult;
import com.edu.scnu.common.vo.Result;
import com.edu.scnu.idnt.dto.DepartmentDTO;
import com.edu.scnu.idnt.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("department")
public class DeptmentAPI {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("create")
    public IResult create(@Valid DepartmentDTO departmentDTO) {
        departmentDTO.setDeptId(null);
        String deptId = departmentService.createDept(departmentDTO);
        return Result.success(deptId);
    }

}
