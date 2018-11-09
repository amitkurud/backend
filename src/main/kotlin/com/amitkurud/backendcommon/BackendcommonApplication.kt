package com.amitkurud.backendcommon

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import java.util.*


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
class BackendCommonApplication {
    var logger = LoggerFactory.getLogger(this.javaClass)!!
    @Bean
    fun commandLineRunner(ctx: ApplicationContext) = CommandLineRunner {
        logger.info("Let's inspect the beans provided by Spring Boot:")
    }
}
fun main(args: Array<String>) {
    runApplication<BackendCommonApplication>(*args)
}
