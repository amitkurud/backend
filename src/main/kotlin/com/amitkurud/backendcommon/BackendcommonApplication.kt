package com.amitkurud.backendcommon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
class BackendCommonApplication

fun main(args: Array<String>) {
    runApplication<BackendCommonApplication>(*args)
}
