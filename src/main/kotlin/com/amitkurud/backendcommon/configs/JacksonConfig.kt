package com.amitkurud.backendcommon.configs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module
import com.fasterxml.jackson.module.afterburner.AfterburnerModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.zalando.problem.validation.ConstraintViolationProblemModule
import org.zalando.problem.ProblemModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer


@Configuration
class JacksonConfig {
    @Bean
    fun hibernate5Module(): Hibernate5Module {
        return Hibernate5Module()
    }

    /*@Bean(name = ["jacksonObjectMapper"])
    fun problemObjectMapperModules(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { jacksonObjectMapperBuilder ->
            jacksonObjectMapperBuilder.modules(problemModule().withStackTraces(false), constraintViolationProblemModule())
        }

    }*/
    /*
     * Jackson Afterburner module to speed up serialization/deserialization.
     */
    @Bean
    fun afterburnerModule(): AfterburnerModule {
        return AfterburnerModule()
    }


    /*
     * Module for serialization/deserialization of RFC7807 Problem.
     */
    /*@Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(problemModule().withStackTraces(false))
    }*/
    @Bean
    fun problemModule(): ProblemModule {
        return ProblemModule()
    }

    @Bean
    fun constraintViolationProblemModule(): ConstraintViolationProblemModule {
        return ConstraintViolationProblemModule()
    }
}