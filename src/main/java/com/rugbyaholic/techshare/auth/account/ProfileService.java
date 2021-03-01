package com.rugbyaholic.techshare.auth.account;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.ImageFile;
import com.rugbyaholic.techshare.common.repositories.UserRepository;
import com.rugbyaholic.techshare.common.util.WritableFile;

@Service
public class ProfileService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static final String PROFILE_IMAGE_DEST = "D:\\img\\00_profile\\";
	
	@Transactional(rollbackFor = Throwable.class)
	public void editProfile(ProfileEditForm form, AuthenticatedUser user) throws Exception {
		
		// DB登録用の画像ファイル名を生成
		MultipartFile uploadFile = form.getUploadFile();
		String absolutePath = PROFILE_IMAGE_DEST + user.getEmpNo() + File.separator;
		
		// アップロードファイルが空でない場合は所定のパスにファイルを格納し
		if (!uploadFile.isEmpty()) {
			
			WritableFile writableFile = new WritableFile(uploadFile);
			writableFile.deleteAndWrite(absolutePath);
			
			// ユーザーオブジェクトが持つ画像情報を更新
			ImageFile imageFile = new ImageFile();
			imageFile.setFileName(absolutePath + uploadFile.getOriginalFilename());
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