package com.rugbyaholic.techshare.manage.sys.fs.upload;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {
	
	String message() default "ファイルサイズが上限を超えています";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	long size();
}