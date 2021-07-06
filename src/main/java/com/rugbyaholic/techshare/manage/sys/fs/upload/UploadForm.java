package com.rugbyaholic.techshare.manage.sys.fs.upload;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
	
	private String path;
	
	@FileSize(size = 10 * SizeUnit.MB)
	private MultipartFile multipartFile;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
}