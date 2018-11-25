package com.amitkurud.backendcommon.controllers.rest

import com.amitkurud.backendcommon.aspects.errorhandling.ErrorDetails
import com.amitkurud.backendcommon.services.implementations.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.zalando.problem.Problem
import java.util.*


@RestController
class RestTest(@Autowired val userService: UserServiceImpl) {
    @GetMapping("/rest/{username}")
    fun test(@PathVariable("username") name: String): ResponseEntity<Any> {
      /*  return if (userService.checkIfUserExists(name))
            ResponseEntity.ok(userService.findUser(name)!!)
        else {
            ResponseEntity.ok(ErrorDetails(timeStamp = Date(),details = "User Not Found with username $name" , message = "User not found"))
            //ResponseEntity.ok(Problem)
        }*/
        return  ResponseEntity.ok(userService.findUser(name)!!)

    }
}
