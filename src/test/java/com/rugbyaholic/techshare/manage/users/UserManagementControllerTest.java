package com.rugbyaholic.techshare.manage.users;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

class UserManagementControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserManagementService service;
	
	@InjectMocks
	private UserManagementController controller;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	@DisplayName("onUserRegistrationRequestedメソッドのテスト")
	void testOnUserRegistrationRequested() throws Exception {
		// Serviceクラスのモックオブジェクトを作成
		UserRegistrationForm form = new UserRegistrationForm(new AuthenticatedUser());
		form.setUsername("Anonymous");
		form.setEmail("anonymous@sample.co.jp");
		Mockito.when(service.initializeRegistrationForm(anyLong()))
			.thenReturn(form);
		
		// Controllerにリクエストを発行
		MvcResult result = mockMvc.perform(get("/manage/users/UserRegistration.html").param("email", "anonymous@sample.co.jp"))
								.andExpect(status().isOk())										// Httpステータスの検査
								.andExpect(view().name("manage/users/UserRegistration.html"))	// 次に表示すべきView名の検査
								.andReturn();
		
		// MvcResultからUserRegistrationFormオブジェクトを取り出し、その内容を検査
		UserRegistrationForm actualForm = (UserRegistrationForm) result.getModelAndView().getModel().get("userRegistrationForm");
		assertEquals(actualForm.getUsername(), form.getUsername());
		assertEquals(actualForm.getEmail(), form.getEmail());
	}

}
