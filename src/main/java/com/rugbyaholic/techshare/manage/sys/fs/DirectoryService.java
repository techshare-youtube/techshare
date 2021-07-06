package com.rugbyaholic.techshare.manage.sys.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Service;

import com.rugbyaholic.techshare.manage.sys.fs.upload.UploadForm;

@Service
public class DirectoryService {
	
	public String homeDirectory() {
		return "D:\\Share";
	}
	
	public void makeDirectory(String currentDirectory, String folderName) {
		String absolutePath = currentDirectory.concat(File.separator)
											.concat(folderName);
		File newDir = new File(absolutePath);
		newDir.mkdir();
	}
	
	public void upload(UploadForm uploadForm) throws IllegalStateException, IOException {
		String absolutePath = uploadForm.getPath().concat(File.separator)
												.concat(uploadForm.getMultipartFile().getOriginalFilename());
		File dest = new File(absolutePath);
		uploadForm.getMultipartFile().transferTo(dest);
	}
	
	public byte[] extractFileData(File sourceFile) throws IOException {
		return Files.readAllBytes(sourceFile.toPath());
	}
}