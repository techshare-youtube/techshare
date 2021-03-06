package com.rugbyaholic.techshare.common.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class WritableFile {
	
	private MultipartFile multipartFile;
	
	public WritableFile(MultipartFile multipartFile) {
		
		this.multipartFile = multipartFile;
	}
	
	public void deleteAndWrite(String path) throws IOException {
		
		if (!multipartFile.isEmpty()) {
			
			// ファイル保存先が存在しない場合は新規作成、存在する場合は配下のファイルとディレクトリを全削除
			File directory = new File(path);
			if (directory.exists()) {
				for (File target : directory.listFiles()) {
					target.delete();
				}
			} else {
				directory.mkdirs();
			}
			
			File dest = new File(path + multipartFile.getOriginalFilename());
			multipartFile.transferTo(dest);
		}
	}
}