package com.amitkurud.backendcommon.controllers

import com.amitkurud.backendcommon.domain.models.User
import com.amitkurud.backendcommon.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping

@Controller
class HomeController(@Autowired var userService: UserService) {
    @GetMapping("/")
    //@TimeTrack
    fun home(model: Model): String {
        model.addAttribute("message", "रजिस्टर कर बे ")
        model.addAttribute("user", User())
        return "index"
    }

    @PostMapping("/")
    fun home(model: Model, @ModelAttribute user: User): String {
        val returnResult =user.let { return@let userService.addUser(user) }
        model.addAttribute("message",returnResult)
        model.addAttribute("user", User())
        return "index"
    }

    @GetMapping("/register")
    fun getRegister(model: Model): String {
        return "redirect:/"
    }
}

