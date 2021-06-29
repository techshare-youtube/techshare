package com.rugbyaholic.techshare.manage.users;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.ImageFile;
import com.rugbyaholic.techshare.common.repositories.CodeRepository;
import com.rugbyaholic.techshare.common.repositories.NumberingRepository;
import com.rugbyaholic.techshare.common.repositories.UserRepository;

@Service
public class UserManagementService {
	
	@Autowired
	private CodeRepository codeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NumberingRepository numberingRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(rollbackFor = Throwable.class)
	public void registerUser(UserRegistrationForm form, AuthenticatedUser user) throws Exception {
		// 社員番号の発番
		if (form.getEmpNo() == null) {
			String availYear = new SimpleDateFormat("yyyy").format(new Date());
			String nextNo = numberingRepository.issueNumber(
						NumberingRepository.NUMBERING_CODE_EMPNO, availYear);
			form.setEmpNo(availYear + nextNo);
			numberingRepository.next(NumberingRepository.NUMBERING_CODE_EMPNO, 
									availYear, user);
		}
		// プロフィール画像の保存
		MultipartFile uploadFile = form.getProfileImage();
		if (!uploadFile.isEmpty()) {
			ImageFile imageFile = new ImageFile();
			imageFile.encode(uploadFile);
			form.setImageFile(imageFile);
		} else {
			form.setImageFile(form.getUser().getProfileImage());
		}
		// ユーザ―情報テーブルの更新
		if (form.getEmail() == null) {
			form.setEmail(form.getUser().getEmail());
			form.setPassword(form.getUser().getPassword());
		} else {
			form.setPassword(passwordEncoder.encode(form.getPassword()));
		}
		userRepository.registerUser(form);
		// ユーザー権限テーブルの更新
		form.setUser(userRepository.findUserById(form.getUser().getId())
										.orElse(new AuthenticatedUser()));
		// 変更前のユーザー権限
		List<String> rolesBefore = form.getUser().getRoles().stream()
									.map(option -> new String(option.getCode()))
									.collect(Collectors.toList());
		// 削除対象のユーザー権限
		List<String> listForDelete = rolesBefore.stream()
										.filter(p -> !form.getRoles().contains(p))
										.collect(Collectors.toList());
		// 新規登録対象のユーザー権限
		List<String> listForGrant = form.getRoles().stream()
										.filter(p -> !rolesBefore.contains(p))
										.collect(Collectors.toList());
		// 権限剥奪
		listForDelete.forEach(role -> {
			userRepository.depriveAuthority(form.getUser(), role);
		});
		// 権限付与
		listForGrant.forEach(role -> {
			userRepository.grantAuthority(form.getUser(), role);
		});
	}
	
	public void restoreRegistrationForm(UserRegistrationForm form) {
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		form.setRoleOptions(codeRepository.getCode(1));
	}
	
	public UserSearchForm initializeSearchForm() {
		UserSearchForm form = new UserSearchForm();
		form.setDeptOptions(codeRepository.getDepertmentCd());
		form.setPosOptions(codeRepository.getPositionCd());
		return form;
	}
	
	public UserRegistrationForm initializeRegistrationForm(Long id) throws Exception {
		UserRegistrationForm form = new UserRegistrationForm(
					userRepository.findUserById(id).orElse(new AuthenticatedUser())
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
		String availYear = new SimpleDateFormat("yyyy").format(new Date());
		user.setEmpNo(availYear + numberingRepository.issueNumber(
				NumberingRepository.NUMBERING_CODE_EMPNO, availYear));
		numberingRepository.next(NumberingRepository.NUMBERING_CODE_EMPNO, availYear, user);
		int updCount = 0;
		updCount += userRepository.registerInitialUser(user);
		updCount += userRepository.grantAuthority(user, "01");
		updCount += userRepository.grantAuthority(user, "02");
		updCount += userRepository.grantAuthority(user, "03");
		if (updCount < 4) throw new Exception();
	}
}