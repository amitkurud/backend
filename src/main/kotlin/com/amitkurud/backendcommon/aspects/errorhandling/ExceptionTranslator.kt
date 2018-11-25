package com.amitkurud.backendcommon.aspects.errorhandling

import com.amitkurud.backendcommon.util.HeaderUtil
import org.springframework.dao.ConcurrencyFailureException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.DefaultProblem
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.spring.web.advice.ProblemHandling
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class ExceptionTranslator : ProblemHandling {
    override fun isCausalChainsEnabled(): Boolean {
        return false
    }
    /**
     * Post-process Problem payload to add the message key for front-end if needed
     */
    override fun process(entity: ResponseEntity<Problem>, request: NativeWebRequest): ResponseEntity<Problem>? {
        if (entity.body == null) {
            return entity
        }
        val problem = entity.body
        if (!(problem is ConstraintViolationProblem || problem is DefaultProblem)) {
            return entity
        }
        val builder = Problem.builder()
                .withType(if (Problem.DEFAULT_TYPE == problem.type) ErrorConstants.DEFAULT_TYPE else problem.type)
                .withStatus(problem.status)
                .withTitle(problem.title)
                .with("path", request.getNativeRequest(HttpServletRequest::class.java)!!.requestURI)

        if (problem is ConstraintViolationProblem) {
            builder
                    .with("violations", problem.violations)
                    .with("message", ErrorConstants.ERR_VALIDATION)
            return ResponseEntity(builder.build(), entity.headers, entity.statusCode)
        } else {
            builder
                    //.withCause((problem as DefaultProblem).cause)
                    //.withDetail(problem.detail)
                    .withInstance(problem.instance)
            problem.parameters.forEach { key, value -> builder.with(key, value) }
            if (!problem.parameters.containsKey("message") && problem.status != null) {
                builder.with("message", "error.http." + problem.status!!.statusCode)
            }
            return ResponseEntity(builder.build(), entity.headers, entity.statusCode)
        }

    }

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, request: NativeWebRequest): ResponseEntity<Problem> {
        val result = ex.bindingResult
        val fieldErrors = result.fieldErrors.stream()
                .map { f -> FieldErrorVM(f.objectName, f.field, f.code!!) }
                .collect(Collectors.toList())

        val problem = Problem.builder()
                .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)
                .withTitle("Method argument not valid")
                .withStatus(defaultConstraintViolationStatus())
                .with("message", ErrorConstants.ERR_VALIDATION)
                .with("fieldErrors", fieldErrors)
                .build()
        return create(problem, request)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException, request: NativeWebRequest): ResponseEntity<Problem> {
        val problem = Problem.builder()
                .withStatus(Status.NOT_FOUND)
                .with("message", ErrorConstants.ENTITY_NOT_FOUND_TYPE)
                .build()
        return create(problem, request)
    }

    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(ex: NullPointerException, request: NativeWebRequest): ResponseEntity<Problem> {
        ex.printStackTrace()
        val problem = Problem.builder()
                .withStatus(Status.NOT_FOUND)
                .with("message", ErrorConstants.ENTITY_NOT_FOUND_TYPE)
                .build()
        return create(ex,problem, request)
    }


    @ExceptionHandler(BadRequestAlertException::class)
    fun handleBadRequestAlertException(ex: BadRequestAlertException, request: NativeWebRequest): ResponseEntity<Problem> {
        return create(ex, request, HeaderUtil.createFailureAlert(ex.entityName, ex.errorKey, ex.message))
    }

    @ExceptionHandler(ConcurrencyFailureException::class)
    fun handleConcurrencyFailure(ex: ConcurrencyFailureException, request: NativeWebRequest): ResponseEntity<Problem> {
        val problem = Problem.builder()
                .withStatus(Status.CONFLICT)
                .with("message", ErrorConstants.ERR_CONCURRENCY_FAILURE)
                .build()
        return create(ex, problem, request)
    }
}

