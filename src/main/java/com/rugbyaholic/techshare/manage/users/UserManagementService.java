package com.rugbyaholic.techshare.manage.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rugbyaholic.techshare.common.repositories.CodeRepository;

@Service
public class UserManagementService {
	
	@Autowired
	private CodeRepository codeRepository;
	
	public UserSearchForm initializeForm() {
		
		UserSearchForm form = new UserSearchForm();
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		
		return form;
	}
}