package com.rugbyaholic.techshare.manage.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.repositories.CodeRepository;
import com.rugbyaholic.techshare.common.repositories.UserRepository;

@Service
public class UserManagementService {
	
	@Autowired
	private CodeRepository codeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserSearchForm initializeSearchForm() {
		
		UserSearchForm form = new UserSearchForm();
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		
		return form;
	}
	
	public int countUser(UserSearchForm form) {
		
		return userRepository.countUser(form);
	}
	
	public List<AuthenticatedUser> loadUserList(UserSearchForm form) {
		
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		return userRepository.loadUserList(form);
	}
}