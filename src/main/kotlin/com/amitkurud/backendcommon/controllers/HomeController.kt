package com.amitkurud.backendcommon.controllers

import com.amitkurud.backendcommon.aspects.TimeTrack
import com.amitkurud.backendcommon.domain.dto.UserDTO
import com.amitkurud.backendcommon.services.implementations.UserServiceImpl
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import java.lang.Exception
import java.util.*
import javax.validation.Valid

@Controller
class HomeController(@Autowired var userServiceImpl: UserServiceImpl) {
    var logger = LoggerFactory.getLogger(this.javaClass)!!
    @GetMapping("/")
    @TimeTrack
    fun home(model: Model): String {
        model.addAttribute("message", "रजिस्टर कर बे ")
        model.addAttribute("userDTO", UserDTO())
        return "index"
    }

    @PostMapping("/")
    fun home(model: Model,@Valid @ModelAttribute userDTO: UserDTO): String {
        var returnResult: String
        try {
            returnResult = userDTO.let {
                userDTO.birthdate = Date()
                return@let userServiceImpl.addUser(userDTO)
            }
            model.addAttribute("message", returnResult)
        } catch (e: Exception) {
            logger.error("")
            returnResult = "Something went wrong"
            model.addAttribute("message", returnResult)
        } finally {
            model.addAttribute("userDTO", UserDTO())
            return "index"
        }
    }

    @GetMapping("/register")
    fun getRegister(model: Model): String {
        return "redirect:/"
    }
}

