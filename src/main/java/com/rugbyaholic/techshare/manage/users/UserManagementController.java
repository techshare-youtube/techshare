package com.rugbyaholic.techshare.manage.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.ui.SearchResult;

@Controller
@Scope("session")
public class UserManagementController {
	
	@Autowired
	private UserManagementService service;
	
	private UserSearchForm form;
	
	public static final int PAGE_LIMIT = 10;
	
	@GetMapping("/manage/users/UserList.html")
	public String onUserListRequested(Model model) {
		
		model.addAttribute("userSearchForm", service.initializeSearchForm());
		return "manage/users/UserList.html";
	}
	
	@GetMapping("/manage/users/UserPageView.do")
	public String onPageViewRequested(@RequestParam("p") int pageNo, Model model) {
		
		model.addAttribute("userSearchForm", form);
		
		SearchResult<AuthenticatedUser> searchResult = new SearchResult<>(service.countUser(form), PAGE_LIMIT);
		if (pageNo < 1 || pageNo > searchResult.getTotalPageCount()) {
			return "manage/users/UserList.html";
		}
		searchResult.moveTo(pageNo);
		form.setPageFrom((pageNo - 1) * PAGE_LIMIT);
		searchResult.setEntities(service.loadUserList(form));
		
		model.addAttribute("searchResult", searchResult);
		return "manage/users/UserList.html";
	}
	
	@GetMapping("/manage/users/UserSearch.do")
	public String onSearchRequested(@ModelAttribute UserSearchForm form, Model model) {
		
		this.form = form;
		
		form.setPageFrom(0);
		form.setCount(PAGE_LIMIT);
		
		SearchResult<AuthenticatedUser> searchResult = new SearchResult<>(service.countUser(form), PAGE_LIMIT);
		searchResult.moveTo(1);
		searchResult.setEntities(service.loadUserList(form));
		
		model.addAttribute("searchResult", searchResult);
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