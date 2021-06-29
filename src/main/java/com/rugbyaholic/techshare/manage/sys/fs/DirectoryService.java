package com.rugbyaholic.techshare.manage.sys.fs;

import org.springframework.stereotype.Service;

@Service
public class DirectoryService {
	
	public String homeDirectory() {
		return "D:\\Share";
	}
}