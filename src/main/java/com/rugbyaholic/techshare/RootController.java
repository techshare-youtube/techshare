package com.rugbyaholic.techshare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.rugbyaholic.techshare.manage.users.UserManagementService;

@Controller
public class RootController {
	
	@Autowired
	private UserManagementService service;
	
	@GetMapping("/")
	public String onActivated(Model model) {
		return "Top.html";
	}
	
	@GetMapping("/Login.html")
	public String onLoginRequested() {
		return "Login.html";
	}
	
	@PostMapping("/Login.err")
	public String onLoginFailed(@RequestAttribute(WebAttributes.AUTHENTICATION_EXCEPTION) 
								AuthenticationException ex,
								Model model) {
		model.addAttribute("authenticationException", ex);
		return "Login.html";
	}
	
	@PostMapping("/UserRegistration.do")
	public String onInitialUserRequested(@RequestParam("email") String email
										,@RequestParam("password") String password
										,Model model) {
		try {
			service.registerInitialUser(email, password);
			
		} catch(Exception ex) {
			System.out.println(ex.getLocalizedMessage());
			return "Login.html";
		}
		
		return "Login.html";
	}
}