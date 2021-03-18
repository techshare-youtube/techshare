package com.rugbyaholic.techshare.manage.users;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.ImageFile;
import com.rugbyaholic.techshare.common.Option;
import com.rugbyaholic.techshare.manage.users.validators.annotations.ConfirmedPassword;
import com.rugbyaholic.techshare.manage.users.validators.annotations.UniqueEmail;

@ConfirmedPassword
public class UserRegistrationForm {
	
	private String empNo;
	
	private MultipartFile profileImage;
	
	private ImageFile imageFile;
	
	@Size(max = 64)
	private String username;
	
	@UniqueEmail
	@Size(max = 128)
	private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date avf;
	
	@Size(min = 8, max = 20)
	private String password;
	
	@Size(min = 8, max = 20)
	private String passwordConfirm;
	
	@NotBlank
	private String deptCd;
	
	@NotBlank
	private String posCd;
	
	@NotNull
	private List<String> roles;
	
	private List<Option> deptOptions;
	
	private List<Option> posOptions;
	
	private List<Option> roleOptions;
	
	private AuthenticatedUser user;
	
	public UserRegistrationForm() {
		this(new AuthenticatedUser());
	}
	
	public UserRegistrationForm(AuthenticatedUser user) {
		this.user = user;
		this.empNo = user.getEmpNo();
		this.imageFile = user.getProfileImage();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.avf = user.getAvf();
		this.deptCd = user.getDeptCd();
		this.posCd = user.getPosCd();
		if (user.getRoles() != null) {
			this.roles = user.getRoles().stream()
							.map(o -> o.getCode())
							.collect(Collectors.toList());
		}
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public MultipartFile getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(MultipartFile profileImage) {
		this.profileImage = profileImage;
	}

	public ImageFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(ImageFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getAvf() {
		return avf;
	}

	public void setAvf(Date avf) {
		this.avf = avf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getPosCd() {
		return posCd;
	}

	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<Option> getDeptOptions() {
		return deptOptions;
	}

	public void setDeptOptions(List<Option> deptOptions) {
		this.deptOptions = deptOptions;
	}

	public List<Option> getPosOptions() {
		return posOptions;
	}

	public void setPosOptions(List<Option> posOptions) {
		this.posOptions = posOptions;
	}

	public List<Option> getRoleOptions() {
		return roleOptions;
	}

	public void setRoleOptions(List<Option> roleOptions) {
		this.roleOptions = roleOptions;
	}

	public AuthenticatedUser getUser() {
		return user;
	}

	public void setUser(AuthenticatedUser user) {
		this.user = user;
	}
}