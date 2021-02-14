package com.edu.scnu.idnt.service.impl;

import java.util.List;
import java.util.Random;

import com.edu.scnu.common.util.ConverterUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Department;
import com.edu.scnu.idnt.bean.DepartmentExample;
import com.edu.scnu.idnt.dto.DepartmentDTO;
import com.edu.scnu.idnt.mapper.DepartmentMapper;
import com.edu.scnu.idnt.query.DepartmentQuery;
import com.edu.scnu.idnt.service.DepartmentService;
import com.edu.scnu.idnt.vo.DepartmentVO;
import tk.mybatis.mapper.common.BaseMapper;

@Service
public class DepartmentServiceImpl extends CommonService<Department, DepartmentDTO, DepartmentVO> implements DepartmentService {

    private DepartmentMapper departmentMapper;

	public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
		super(Department.class, DepartmentDTO.class, DepartmentVO.class, departmentMapper);
		this.departmentMapper = departmentMapper;
	}

	@Override
	public PageVO<DepartmentVO> queryPage(DepartmentQuery query) {
		
		DepartmentExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<Department> resultList = departmentMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<DepartmentVO> queryList(DepartmentQuery query) {
		query.setPageFlag(false);
		PageVO<DepartmentVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	@Override
	public String createDept(DepartmentDTO departmentDTO) {
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
		return deptId;
	}

	private class ExampleProxy extends DepartmentExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
