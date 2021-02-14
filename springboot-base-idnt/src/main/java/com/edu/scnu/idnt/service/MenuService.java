package com.edu.scnu.idnt.service;

import java.util.List;

import com.edu.scnu.common.service.IService;
import com.edu.scnu.common.vo.PageVO;
import com.edu.scnu.idnt.bean.Menu;
import com.edu.scnu.idnt.dto.MenuDTO;
import com.edu.scnu.idnt.query.MenuQuery;
import com.edu.scnu.idnt.vo.MenuVO;

/**
 * if your service don't need this method  you can remove it manually
 */
public interface MenuService extends IService<Menu, MenuDTO, MenuVO> {
	
	PageVO<MenuVO> queryPage(MenuQuery query);
	
	List<MenuVO> queryList(MenuQuery query);
	
}
