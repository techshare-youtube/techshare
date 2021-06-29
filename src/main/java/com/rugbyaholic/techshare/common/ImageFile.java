package com.rugbyaholic.techshare.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageFile {
	
	private String fileName;
	
	private String encodedString;
	
	public void setEncodedString(String encodedString) {
		this.encodedString = encodedString;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void encode(MultipartFile multipartFile) throws IllegalStateException, IOException {
		fileName = multipartFile.getOriginalFilename();
		multipartFile.transferTo(Paths.get(multipartFile.getOriginalFilename()));
	}
	
	/**
	 * BASE64でエンコードしたファイルデータを文字列で返す。
	 * @return
	 */
	public String getEncodedString() {
		if(StringUtils.hasText(encodedString)) {
			return encodedString;
		}
		
		if (!StringUtils.hasText(fileName)) {
			fileName = getClass().getResource("anonymous.png").getPath();
		}
		File imageFile = new File(fileName);
		
		StringBuffer base64String = new StringBuffer();
		try {
			String contentType = Files.probeContentType(imageFile.toPath());
			byte[] fileData = Files.readAllBytes(imageFile.toPath());
			
			base64String.append("data:");
			base64String.append(contentType);
			base64String.append(";base64,");
			base64String.append(Base64.getEncoder().encodeToString(fileData));
		} catch (IOException e) {
			return "";
		}
		
		if (!fileName.equals(getClass().getResource("anonymous.png").getPath())) {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
		}
		encodedString = base64String.toString();
		return encodedString;
	}
}