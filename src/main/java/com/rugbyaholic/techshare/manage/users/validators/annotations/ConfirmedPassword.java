package com.rugbyaholic.techshare.manage.users.validators.annotations;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.rugbyaholic.techshare.manage.users.validators.ConfirmedPasswordValidator;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = ConfirmedPasswordValidator.class)
public @interface ConfirmedPassword {
	
	String message() default "パスワードとパスワード再入力が一致しません";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}