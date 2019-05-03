package com.edu.scnu.user.vo;

import lombok.Data;

@Data
public class UploadVo {
	
	private Boolean isSuccess;
	
	private String uploadPath;
	
	private String url;
	
	private String msg;
	
	private Throwable exception;
	
	public static UploadVo success(String path, String url) {
		UploadVo uploadVo = new UploadVo();
		uploadVo.setIsSuccess(true);
		uploadVo.setUploadPath(path);
		uploadVo.setUrl(url);
		return uploadVo;
	}
	
	public static UploadVo error(String msg) {
		UploadVo uploadVo = new UploadVo();
		uploadVo.setIsSuccess(false);
		uploadVo.setMsg(msg);
		return uploadVo;
	}
	
	public static UploadVo error(String msg, Throwable e) {
		UploadVo uploadVo = error(msg);
		uploadVo.setException(e);
		return uploadVo;
	}
}
