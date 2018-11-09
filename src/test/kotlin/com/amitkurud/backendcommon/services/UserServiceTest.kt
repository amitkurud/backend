package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.domain.models.User
import org.junit.Test
import org.junit.runner.RunWith

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    var userService: UserService? = null

    @Test
    fun addUser() {
        for (count in 0..10) {
            val user = User()
            user.username = count.toString()
            user.password = count.toString()
            println(userService?.addUser(user))
        }
        for (count in 0..10) {
            val user = User()
            user.username = count.toString()
            user.password = count.toString()
            println(userService?.addUser(user))
        }
    }

}