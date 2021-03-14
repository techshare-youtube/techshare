package com.rugbyaholic.techshare.manage.users.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rugbyaholic.techshare.auth.DatabaseUserDetailsService;
import com.rugbyaholic.techshare.manage.users.validators.annotations.UniqueEmail;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
	
	@Autowired
	private DatabaseUserDetailsService service;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			service.loadUserByUsername(value);
			return false;
		} catch (UsernameNotFoundException ex) {
			return true;
		}
	}
}