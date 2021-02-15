package com.edu.scnu.admin.vo;

import com.edu.scnu.admin.bean.Department;
import com.edu.scnu.admin.bean.Role;
import com.edu.scnu.admin.bean.User;
import lombok.Data;

import java.util.List;

@Data
public class UserEntity {

    private User user;

    private Department currentDept;

    private Role currentRole;

    private List<DeptRoleEntity> deptRoleList;

}

