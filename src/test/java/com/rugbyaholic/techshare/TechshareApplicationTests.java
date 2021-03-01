package com.rugbyaholic.techshare;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.rugbyaholic.techshare.common.repositories.NumberingRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	TransactionalTestExecutionListener.class,
	DbUnitTestExecutionListener.class
})
class TechshareApplicationTests {
	
	@Autowired
	private NumberingRepository numberingRepository;
	
	@Test
	@Transactional
	public void test() {
		
		String actualEmpNo = numberingRepository.issue("E00", "2021");
		String expectedEmpNo = "21000002";
		numberingRepository.increase("E00", "2021", 121);
		
		assertEquals(expectedEmpNo, actualEmpNo);
	}
}