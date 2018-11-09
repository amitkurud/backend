package com.amitkurud.backendcommon.aspects

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class LoggingAspect {

    private val logger = LoggerFactory.getLogger(LoggingAspect::javaClass.javaClass)
    @Pointcut("this(org.springframework.data.repository.Repository)" +
            " || within(@org.springframework.stereotype.Service *)"
            //+" || within(@org.springframework.stereotype.Controller *)"
    )
    fun springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("within(com.amitkurud.backendcommon.domain.repository..*)" +
            " || within(com.amitkurud.backendcommon.services..*)"
            + " || within(com.amitkurud.backendcommon.controllers..*)")
    fun applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("@annotation(com.amitkurud.backendcommon.aspects.TimeTrack)")
    fun timerPointcut() {
    }

    @Around("timerPointcut()")
    @Throws(Throwable::class)
    fun measure(joinPoint: ProceedingJoinPoint): Any {
        val startTime = System.currentTimeMillis()
        var result = joinPoint.proceed()
        val timeTaken = System.currentTimeMillis() - startTime
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken)
        return result
    }

    @Around(value = "applicationPackagePointcut() & springBeanPointcut()")
    @Throws(Throwable::class)
    fun around(joinPoint: ProceedingJoinPoint): Any {
        logger.info("Enter: {}.{}() with argument[s] = {}", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, Arrays.toString(joinPoint.args))
        val result = joinPoint.proceed()
        logger.info("Exit: {}.{}() with result = {}", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, result)
        return result
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut() && springBeanPointcut()", throwing = "e")
    fun logAfterThrowing(joinPoint: JoinPoint, e: Throwable) {
        logger.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, if (e.cause != null) e.cause else "NULL", e.message, e)
    }
}
