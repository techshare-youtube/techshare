package com.rugbyaholic.techshare.manage.users;

import java.util.List;

import com.rugbyaholic.techshare.common.Option;

public class UserSearchForm {
	
	private String empNo;
	
	private String name;
	
	private String deptCd;
	
	private String posCd;
	
	private List<Option> deptOptions;
	
	private List<Option> posOptions;
	
	private int pageFrom;
	
	private int count;
	
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(int pageFrom) {
		this.pageFrom = pageFrom;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}