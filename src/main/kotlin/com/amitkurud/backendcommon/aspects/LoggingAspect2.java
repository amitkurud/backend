/*
package com.amitkurud.backendcommon.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect2 {
    private Logger log = LoggerFactory.getLogger(LoggingAspect2.class);

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.stereotype.Controller *)")
    void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    */
/**
     * Pointcut that matches all Spring beans in the application's main packages.
     *//*

    @Pointcut("within(com.amitkurud.backendcommon.domain.repository..*)" +
            " || within(com.amitkurud.backendcommon.service..*)")
    void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("@annotation(com.amitkurud.backendcommon.aspects.TimeTrack)")
    void timerPointcut() {
    }

    @Around(value = "applicationPackagePointcut() & springBeanPointcut()")
    Object loggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("hhihihihihihihi ");
        log.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringType(),
                joinPoint.getSignature().getName(), result);
        return result;
    }

}
*/
