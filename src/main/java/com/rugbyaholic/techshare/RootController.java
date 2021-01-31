package com.rugbyaholic.techshare;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

@Controller
public class RootController {
	
	@GetMapping("/")
	public String onActivated(@AuthenticationPrincipal AuthenticatedUser user,
								Model model) {
		model.addAttribute("authenticatedUser", user);
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
}