package com.rugbyaholic.techshare.manage.users;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.ui.SearchResult;
import com.rugbyaholic.techshare.common.util.NotificationMessage;

@Controller
@Scope("session")
public class UserManagementController {
	
	@Autowired
	private UserManagementService service;
	
	@Autowired
	private NotificationMessage notificationMessage;
	
	private UserSearchForm form;
	
	public static final int PAGE_LIMIT = 10;
	
	@GetMapping("/manage/users/UserList.html")
	public String onUserListRequested(Model model) {
		
		model.addAttribute("userSearchForm", service.initializeSearchForm());
		return "manage/users/UserList.html";
	}
	
	@PostMapping("/manage/users/UserRegistration.do")
	public String onUserUpdateRequested(@Valid @ModelAttribute UserRegistrationForm userRegistrationForm,
										BindingResult bindingResult,
										@AuthenticationPrincipal AuthenticatedUser user,
										Model model) {
		if (bindingResult.hasErrors()) {
			service.restoreRegistrationForm(userRegistrationForm);
			model.addAttribute("userRegistrationForm", userRegistrationForm);
			return "/manage/users/UserRegistration.html";
		} else {
			try {
				service.registerUser(userRegistrationForm, user);
				model.addAttribute("userRegistrationForm", service.initializeRegistrationForm(
									userRegistrationForm.getUser().getId()));
				model.addAttribute("notificationMessage", notificationMessage.builder()
						.messageLevel(NotificationMessage.MESSAGE_LEVEL_SUCCESS)
						.messageCode("techshare.web.message.proc.success")
						.build());
			} catch (Exception e) {
				// TODO エラー画面開発後に実装
			}
			return "/manage/users/UserRegistration.html";
		}
	}
	
	@GetMapping("/manage/users/UserRegistration.html")
	public String onUserRegistrationRequested(@RequestParam(value = "id", required = false)Long id,
												Model model) {
		try {
			UserRegistrationForm form = service.initializeRegistrationForm(id);
			model.addAttribute("userRegistrationForm", form);
		} catch (Exception e) {
			// TODO エラー画面開発後に実装
		}
		return "manage/users/UserRegistration.html";
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