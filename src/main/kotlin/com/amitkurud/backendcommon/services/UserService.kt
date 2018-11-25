package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.domain.dto.UserDTO
import com.amitkurud.backendcommon.domain.models.User

interface UserService {
    fun addUser(userDTO: UserDTO):String
    fun findUser(username : String) : User?
    fun checkIfUserExists(username: String) : Boolean
}