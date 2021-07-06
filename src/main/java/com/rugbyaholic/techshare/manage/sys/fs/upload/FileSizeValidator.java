package com.rugbyaholic.techshare.manage.sys.fs.upload;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
	
	private long sizeLimit;
	
	@Override
	public void initialize(FileSize constraintAnnotation) {
		sizeLimit = constraintAnnotation.size();
	}
	
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		return value.getSize() <= sizeLimit;
	}
}