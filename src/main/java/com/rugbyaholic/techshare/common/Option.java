package com.rugbyaholic.techshare.common;

public class Option {
	
	private String code;
	
	private String name;
	
	public Option() {
		this("", "");
	}
	
	public Option(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Option target = (Option)obj;
		return target.code.equals(code) && target.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return (code + name).hashCode();
	}
}