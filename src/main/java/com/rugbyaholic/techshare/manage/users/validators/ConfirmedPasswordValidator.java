package com.rugbyaholic.techshare.manage.users.validators;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rugbyaholic.techshare.manage.users.UserRegistrationForm;
import com.rugbyaholic.techshare.manage.users.validators.annotations.ConfirmedPassword;

public class ConfirmedPasswordValidator implements ConstraintValidator<ConfirmedPassword, UserRegistrationForm> {
	
	@Override
	public boolean isValid(UserRegistrationForm value, ConstraintValidatorContext context) {
		if (Objects.equals(value.getPassword(), value.getPasswordConfirm())) {
			return true;
		} else {
			context.buildConstraintViolationWithTemplate("パスワードとパスワード再入力が一致しません")
				.addPropertyNode("passwordConfirm")
				.addConstraintViolation();
			return false;
		}
	}
}