package com.rugbyaholic.techshare.manage.users;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;
import com.rugbyaholic.techshare.common.Option;
import com.rugbyaholic.techshare.common.repositories.CodeRepository;
import com.rugbyaholic.techshare.common.repositories.UserRepository;

class UserManagementServiceTest {
	
	@Mock
	private CodeRepository codeRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserManagementService service;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testInitializeRegistrationForm() throws Exception {
		// CodeRepositoryの動作を定義
		Mockito.when(codeRepository.getDepertmentCd())
			.thenReturn(List.of(new Option("00000000", "システム管理")));
		Mockito.when(codeRepository.getPositionCd())
			.thenReturn(List.of(new Option("0000", "社長")));
		Mockito.when(codeRepository.getCode(ArgumentMatchers.anyLong()))
			.thenReturn(List.of(new Option("01", "ROLE_USER")));
		// UserRepositoryの動作を定義
		Mockito.when(userRepository.findUserById(ArgumentMatchers.anyLong()))
			.thenReturn(Optional.of(new AuthenticatedUser()));
		
		UserRegistrationForm actual = service.initializeRegistrationForm(0l);
		
		assertThat(actual.getDeptOptions()).hasSize(1)
			.extracting(Option::getCode, Option::getName)
			.containsExactly(
						tuple("00000000", "システム管理")
					);
		assertThat(actual.getPosOptions()).hasSize(1)
			.extracting(Option::getCode, Option::getName)
			.containsExactly(
						tuple("0000", "社長")
					);
		assertThat(actual.getRoleOptions()).hasSize(1)
			.extracting(Option::getCode, Option::getName)
			.containsExactly(
						tuple("01", "ROLE_USER")
					);
			
	}
}