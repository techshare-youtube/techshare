package com.rugbyaholic.techshare.common.repositories;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.auth.account.ProfileEditForm;
import com.rugbyaholic.techshare.manage.users.UserRegistrationForm;
import com.rugbyaholic.techshare.manage.users.UserSearchForm;

@Mapper
public interface UserRepository {
	
	public void depriveAuthority(@Param("user") AuthenticatedUser user, @Param("role") String userRole);
	
	public void registerUser(UserRegistrationForm form);
	
	public Optional<AuthenticatedUser> findUserById(Long id);
	
	public Optional<AuthenticatedUser> identifyUser(String email);
	
	public Optional<ProfileEditForm> createProfileEditForm(long userId);
	
	public int changeProfile(AuthenticatedUser user);
	
	public int updatePersonalInfo(ProfileEditForm profileEditForm);
	
	public List<AuthenticatedUser> loadUserList(UserSearchForm form);
	
	public int countUser(UserSearchForm form);
	
	public int registerInitialUser(AuthenticatedUser user);
	
	public int grantAuthority(@Param("user") AuthenticatedUser user, @Param("role") String userRole);
	
}