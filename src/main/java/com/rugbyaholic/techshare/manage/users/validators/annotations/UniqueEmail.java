package com.rugbyaholic.techshare.manage.users.validators.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.rugbyaholic.techshare.manage.users.validators.UniqueEmailValidator;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
	
	String message() default "既に登録済みのメールアドレスが指定されました";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}