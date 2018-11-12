package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.aspects.TimeTrack
import com.amitkurud.backendcommon.domain.dto.UserDTO
import com.amitkurud.backendcommon.services.implementations.UserServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    var userServiceImpl: UserServiceImpl? = null

    @Test
    @TimeTrack
    fun addUser() {
        for (count in 0..1000) {
            val user = UserDTO()
            user.username = count.toString()
            user.password = count.toString()
            println(userServiceImpl?.addUser(user))
        }
        for (count in 0..10) {
            val user = UserDTO()
            user.username = count.toString()
            user.password = count.toString()
            println(userServiceImpl?.addUser(user))
        }
        val user = UserDTO()
        user.username = "a".repeat(2555)
        user.password = "a".repeat(2555)
        println(userServiceImpl?.addUser(user))
    }

}