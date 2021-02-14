package com.edu.scnu.idnt.service;

import java.util.List;

import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Department;
import com.edu.scnu.idnt.dto.DepartmentDTO;
import com.edu.scnu.idnt.query.DepartmentQuery;
import com.edu.scnu.idnt.vo.DepartmentVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface DepartmentService extends IService<Department, DepartmentDTO, DepartmentVO> {
	
	PageVO<DepartmentVO> queryPage(DepartmentQuery query);
	
	List<DepartmentVO> queryList(DepartmentQuery query);

	/**
	 * 新增机构，返回机构编号
	 * @param departmentDTO
	 * @return
	 */
    String createDept(DepartmentDTO departmentDTO);
}
