package com.harbour.todomvc;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodInvocationAspect {
	
	@Around("execution(* com.harbour.todomvc.*Controller.*(..))")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String clazz = proceedingJoinPoint.getTarget().getClass().getName();
		Logger logger = LoggerFactory.getLogger(clazz);

		Signature signature = proceedingJoinPoint.getSignature();
		logger.info("Enter {} ", signature.toShortString());
		
		Object[] args = proceedingJoinPoint.getArgs();
		logger.info("arguments: {} ", Arrays.toString(args));
		
		Object result = proceedingJoinPoint.proceed();
		
		logger.info("Exit {} ", signature.toShortString());
		return result;
	}
	
}
