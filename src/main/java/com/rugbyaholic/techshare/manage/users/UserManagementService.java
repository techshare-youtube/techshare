package com.rugbyaholic.techshare.manage.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.repositories.CodeRepository;
import com.rugbyaholic.techshare.common.repositories.UserRepository;

@Service
public class UserManagementService {
	
	@Autowired
	private CodeRepository codeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserSearchForm initializeSearchForm() {
		
		UserSearchForm form = new UserSearchForm();
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		
		return form;
	}
	
	public UserRegistrationForm initializeRegistrationForm(String email) throws Exception {
		UserRegistrationForm form = new UserRegistrationForm(
					userRepository.identifyUser(email).orElse(new AuthenticatedUser())
				);
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		form.setRoleOptions(codeRepository.getCode(1));
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
	
	@Transactional(rollbackFor = Throwable.class)
	public void registerInitialUser(String email, String password) throws Exception {
		
		AuthenticatedUser user = new AuthenticatedUser();
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setUsername("初期ユーザー");
		user.setEmpNo("99999999");
		
		int updCount = 0;
		updCount += userRepository.registerInitialUser(user);
		updCount += userRepository.grantAuthority(user, "01");
		updCount += userRepository.grantAuthority(user, "02");
		updCount += userRepository.grantAuthority(user, "03");
		
		if (updCount < 4) throw new Exception();
	}
}