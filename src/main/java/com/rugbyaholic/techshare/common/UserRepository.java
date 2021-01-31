package com.rugbyaholic.techshare.common;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

@Mapper
public interface UserRepository {
	
	public Optional<AuthenticatedUser> identifyUser(String email);
}