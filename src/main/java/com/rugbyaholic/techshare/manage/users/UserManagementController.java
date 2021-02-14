package com.rugbyaholic.techshare.manage.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserManagementController {
	
	@Autowired
	private UserManagementService service;
	
	@GetMapping("/manage/users/UserList.html")
	public String onUserListRequested(Model model) {
		
		model.addAttribute("userSearchForm", service.initializeForm());
		return "manage/users/UserList.html";
	}
}