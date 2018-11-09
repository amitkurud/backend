package com.amitkurud.backendcommon.controllers.rest

import com.amitkurud.backendcommon.domain.models.User
import com.amitkurud.backendcommon.domain.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class RestTest(@Autowired val userRepository : UserRepository){
  @GetMapping("/rest/{username}")
  fun test(@PathVariable("username") a : String ) : User? {
    return userRepository.findByUsername(a)
  }
}
