package com.codegym.c0324h1_spring_boot_2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class StudentAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.codegym.c0324h1_spring_boot_2.controllers.StudentController.*(..))")
    public void logAfterVisitStudentController(JoinPoint joinPoint) {
            logger.info("Người dùng đã vào chức năng "+ joinPoint.getSignature().getName()
            + " vào lúc "+ LocalDateTime.now());
    }
    
}
