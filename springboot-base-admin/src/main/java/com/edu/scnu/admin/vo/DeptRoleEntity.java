package com.edu.scnu.admin.vo;

import com.edu.scnu.admin.bean.Department;
import com.edu.scnu.admin.bean.Role;
import lombok.Data;

@Data
public class DeptRoleEntity {

    private Department department;

    private Role role;

    private Boolean isDefault;
}
