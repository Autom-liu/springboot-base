package com.edu.scnu.user.service;

import java.io.InputStream;

import com.edu.scnu.user.vo.UploadVo;

public interface FileService {
	
	/**
	 * 上传文件，重新生成新的文件名，同时生成父级目录
	 * @param uploadFile
	 * @param originName
	 * @param namespace
	 * @return
	 */
	UploadVo upload(InputStream uploadFile, String originName, String namespace);
	
	/**
	 * 上传文件，能重新生成新的文件名
	 * @param uploadFile
	 * @param originName 原文件名
	 * @return
	 */
	UploadVo upload(InputStream uploadFile, String originName);
	
	/**
	 * 上传文件，将文件上传到指定路径，返回相对路径结果
	 * @param uploadFile
	 * @param fileName
	 * @return
	 */
	UploadVo uploadCore(InputStream uploadFile, String fileName);
	
	
	
	void delete(String filename);
}
