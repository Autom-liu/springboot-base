package com.edu.scnu.common.service;

import java.util.List;

import com.edu.scnu.common.exception.BizException;

/**
 * 公共接口的服务
 * @author Autom
 *
 * @param <T> BO 实体类
 * @param <DTO> DTO
 * @param <VO> VO
 */
public interface IService<T, DTO, VO> {
	
	List<VO> findAll() throws BizException;
	
	VO findOne(Integer id) throws BizException;
	
	List<VO> findByIds(List<Integer> ids) throws BizException;
	
	int insert(DTO dto) throws BizException;
	
	int deleteById(Integer id) throws BizException;
	
	int deleteByIds(List<Integer> ids) throws BizException;
	
	int updateByIdSelective(DTO dto) throws BizException;
	
}
