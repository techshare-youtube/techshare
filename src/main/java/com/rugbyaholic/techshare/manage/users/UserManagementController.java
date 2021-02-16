package com.rugbyaholic.techshare.manage.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

@Controller
public class UserManagementController {
	
	@Autowired
	private UserManagementService service;
	
	@GetMapping("/manage/users/UserList.html")
	public String onUserListRequested(Model model) {
		
		model.addAttribute("userSearchForm", service.initializeSearchForm());
		return "manage/users/UserList.html";
	}
	
	@GetMapping("/manage/user/UserSearch.do")
	public String onSearchRequested(@ModelAttribute UserSearchForm form, Model model) {
		
		List<AuthenticatedUser> userList = service.loadUserList(form);
		model.addAttribute("userList", userList);
		model.addAttribute("userSearchForm", form);
		return "manage/users/UserList.html";
	}
	
	/**
	 * 未入力項目はバリデーションの対象外とするメソッド
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}