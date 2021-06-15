package com.rugbyaholic.techshare.auth.account;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.ImageFile;
import com.rugbyaholic.techshare.common.repositories.UserRepository;

@Service
public class ProfileService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(rollbackFor = Throwable.class)
	public void editProfile(ProfileEditForm form, AuthenticatedUser user) throws Exception {
		
		// DB登録用の画像ファイル名を生成
		MultipartFile uploadFile = form.getUploadFile();
		
		if (!uploadFile.isEmpty()) {
			ImageFile imageFile = new ImageFile();
			imageFile.encode(uploadFile);
			user.setProfileImage(imageFile);
		}
		
		// DB更新件数
		int updateCount = 0;
		
		// ユーザ―情報更新
		if (form.getPassword() != null) {
			user.setPassword(passwordEncoder.encode(form.getPassword()));
		}
		updateCount += repository.changeProfile(user);
		
		// 個人情報更新
		form.setUserId(user.getId());
		updateCount += repository.updatePersonalInfo(form);
		
		if (updateCount < 2) throw new Exception();
	}
	
	public ProfileEditForm providePersonalInfo(AuthenticatedUser user) {
		
		// ユーザーの個人情報を取得
		Optional<ProfileEditForm> optionalForm = repository.createProfileEditForm(user.getId());
		return optionalForm.orElse(new ProfileEditForm());
	}
}