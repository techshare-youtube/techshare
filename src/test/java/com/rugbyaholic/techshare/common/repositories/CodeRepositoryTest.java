package com.rugbyaholic.techshare.common.repositories;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.rugbyaholic.techshare.common.Option;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CodeRepositoryTest {
	
	@Autowired
	private CodeRepository codeRepository;

	@Test
	void testGetCode() {
		List<Option> roles = codeRepository.getCode(1);
		assertThat(roles).hasSize(3)
			.extracting(Option::getCode, Option::getName)
			.containsExactly(
						tuple("01", "ROLE_USER"),
						tuple("02", "ROLE_MANAGER"),
						tuple("03", "ROLE_ADMIN")
					);
	}
}