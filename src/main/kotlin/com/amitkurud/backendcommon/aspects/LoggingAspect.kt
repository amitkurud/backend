package com.amitkurud.backendcommon.aspects

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Aspect
@Component
class LoggingAspect {

    private val log = LoggerFactory.getLogger(LoggingAspect::javaClass.javaClass)
    @Pointcut("@annotation(org.springframework.stereotype.Repository)" +
            " || within(@org.springframework.stereotype.Service *)"
    //+" || within(@org.springframework.stereotype.Controller *)"
    )
    fun springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("within(com.amitkurud.backendcommon.domain.repository..*)" +
            " || within(com.amitkurud.backendcommon.services..*)"
    +" || within(com.amitkurud.backendcommon.controllers..*)")
    fun applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("@annotation(com.amitkurud.backendcommon.aspects.TimeTrack)")
    fun timerPointcut() {
    }
    @Around("timerPointcut()")
    @Throws(Throwable::class)
    fun measure(joinPoint: ProceedingJoinPoint): Any {
        println("Before proceed")
        val obj = joinPoint.proceed()
        println("After proceed")
        return obj
    }
    @Around(value = "applicationPackagePointcut() & springBeanPointcut()")
    //@Around("@annotation(TimeTrack)")
    @Throws(Throwable::class)
    fun around(joinPoint: ProceedingJoinPoint): Any {
        println("Begin Logging")
        log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, Arrays.toString(joinPoint.args))
        val result = joinPoint.proceed()
        log.info("Exit: {}.{}() with result = {}", joinPoint.signature.declaringTypeName,
                joinPoint.signature.name, result)
        println("End Logging")
        return result
    }
}
