package com.edu.scnu.user.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.edu.scnu.common.utils.KeyUtils;
import com.edu.scnu.user.config.ResourceConfig;
import com.edu.scnu.user.service.FileService;
import com.edu.scnu.user.vo.UploadVo;

import lombok.Cleanup;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	private ResourceConfig resourceConfig;

	@Override
	public UploadVo uploadCore(InputStream uploadFile, String fileName) {
		if (uploadFile != null) {
			String savePath = resourceConfig.getSavePath();
			File outFile = new File(savePath + fileName);
			if(outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
				outFile.getParentFile().mkdirs();
			}
			
			try {
				@Cleanup OutputStream finalFile = new FileOutputStream(outFile);
				IOUtils.copy(uploadFile, finalFile);
			} catch (IOException e) {
				return UploadVo.error("上传文件发生异常", e);
			}
			return UploadVo.success(fileName, resourceConfig.getVisitPath() + fileName);
		}
		return UploadVo.error("上传文件不能为空");
	}

	@Override
	public UploadVo upload(InputStream uploadFile, String originName, String namespace) {
		if (StringUtils.isEmpty(namespace)) {
			return upload(uploadFile, originName);
		}
		String fileName = KeyUtils.stringWithTime(20, "");
		String ext = FilenameUtils.getExtension(originName);
		String finalFileName = namespace + File.separator + fileName + "." + ext;
		return uploadCore(uploadFile, finalFileName);
	}

	@Override
	public UploadVo upload(InputStream uploadFile, String originName) {
		String fileName = KeyUtils.stringWithTime(20, "");
		String ext = FilenameUtils.getExtension(originName);
		String finalFileName = fileName + "." + ext;
		return uploadCore(uploadFile, finalFileName);
	}
	
	
	@Override
	public void delete(String filename) {
		File file = new File(filename);
		file.delete();
	}
}
