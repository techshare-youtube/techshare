package com.rugbyaholic.techshare.common.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.rugbyaholic.techshare.auth.AuthenticatedUser;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class NumberingRepositoryTest {
	
	@Autowired
	private NumberingRepository repository;
	
	@Mock
	private AuthenticatedUser user;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void issueNumberTest() {
		String issuedNumber = repository.issueNumber("E00", "2021");
		assertEquals("0001", issuedNumber);
	}
	
	@Test
	void nextTest() {
		Mockito.when(user.getId()).thenReturn(1l);
		repository.next("E00", "2021", user);
		String issuedNumber = repository.issueNumber("E00", "2021");
		assertEquals("0002", issuedNumber);
	}
}