package com.rugbyaholic.techshare.manage.users;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rugbyaholic.techshare.common.ImageFile;
import com.rugbyaholic.techshare.common.Option;

public class UserRegistrationForm {
	
	private long id;
	
	private String empNo;
	
	private String username;
	
	private String email;
	
	private Date avf;
	
	private String password;
	
	private String passwordConfirm;
	
	private ImageFile imageFile;
	
	private MultipartFile multipartFile;
	
	private String deptCd;
	
	private String posCd;
	
	private List<String> authorities;
	
	private List<Option> deptOptions;
	
	private List<Option> posOptions;
	
	private List<Option> authorityOptions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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

	public ImageFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(ImageFile imageFile) {
		this.imageFile = imageFile;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
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

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
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

	public List<Option> getAuthorityOptions() {
		return authorityOptions;
	}

	public void setAuthorityOptions(List<Option> authorityOptions) {
		this.authorityOptions = authorityOptions;
	}
}