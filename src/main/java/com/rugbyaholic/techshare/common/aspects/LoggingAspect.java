package com.rugbyaholic.techshare.common.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("@annotation(com.rugbyaholic.techshare.common.aspects.LogRequired)")
	public void logStarted(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		logger.info("{}#{} Started.", className, methodName);
	}
	
	@AfterReturning("@annotation(com.rugbyaholic.techshare.common.aspects.LogRequired)")
	public void logEnded(JoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		logger.info("{}#{} Normally Ended.", className, methodName);
	}
	
	@AfterThrowing(value = "@annotation(com.rugbyaholic.techshare.common.aspects.LogRequired)",
					throwing = "ex")
	public void logExceptionThrown(JoinPoint joinPoint, Throwable ex) {
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		logger.info("{}#{} throws {}({}) ", className, methodName,
					ex.getClass().getSimpleName(), ex.getLocalizedMessage());
	}
}