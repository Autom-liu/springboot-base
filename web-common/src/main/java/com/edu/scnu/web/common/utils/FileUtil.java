package com.edu.scnu.web.common.utils;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.edu.scnu.user.service.FileService;
import com.edu.scnu.user.vo.UploadVo;

import lombok.Cleanup;

public class FileUtil {
	
	@Autowired
	private FileService fileService;
	
	private static FileUtil instance = new FileUtil();
	
	private FileUtil() {}
	
	public static UploadVo upload(MultipartFile uploadFile, String namespace) {
		return instance.uploadFile(uploadFile, namespace);
	}
	
	private UploadVo uploadFile(MultipartFile uploadFile, String namespace) {
		if (uploadFile != null && uploadFile.getSize() > 0) {
			
			try {
				String originName = uploadFile.getOriginalFilename();
				@Cleanup InputStream inputStream = uploadFile.getInputStream();
				return fileService.upload(inputStream, originName, namespace);
			} catch (IOException e) {
				return UploadVo.error("文件上传发生异常：", e);
			}
		} else {
			return UploadVo.error("上传的文件不能为空！");
		}
	}

}
