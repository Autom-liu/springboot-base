package com.edu.scnu.idnt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.scnu.common.proxy.CriteriaProxy;
import com.edu.scnu.common.service.CommonService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Menu;
import com.edu.scnu.idnt.bean.MenuExample;
import com.edu.scnu.idnt.dto.MenuDTO;
import com.edu.scnu.idnt.mapper.MenuMapper;
import com.edu.scnu.idnt.query.MenuQuery;
import com.edu.scnu.idnt.service.MenuService;
import com.edu.scnu.idnt.vo.MenuVO;
import tk.mybatis.mapper.common.BaseMapper;

@Service
public class MenuServiceImpl extends CommonService<Menu, MenuDTO, MenuVO> implements MenuService {

    private MenuMapper menuMapper;

	public MenuServiceImpl(MenuMapper menuMapper) {
		super(Menu.class, MenuDTO.class, MenuVO.class, menuMapper);
		this.menuMapper = menuMapper;
	}

	@Override
	public PageVO<MenuVO> queryPage(MenuQuery query) {
		
		MenuExample example = new ExampleProxy();
		
		super.handlePageOrder(query, false, example);
		
		// TODO Here build the condition you want
		
		List<Menu> resultList = menuMapper.selectByExample(example);
		
		return super.handlePageResult(resultList);
	}
	

	@Override
	public List<MenuVO> queryList(MenuQuery query) {
		query.setPageFlag(false);
		PageVO<MenuVO> pageVO = this.queryPage(query);
		return pageVO.getData();
	}

	private class ExampleProxy extends MenuExample {
		// 静态代理一下
		public Criteria createCriteria() {
			return (Criteria) CriteriaProxy.getInstance(super.createCriteria());
		}
		
		public Criteria or() {
			return (Criteria) CriteriaProxy.getInstance(super.or());
		}
		
	}
}
