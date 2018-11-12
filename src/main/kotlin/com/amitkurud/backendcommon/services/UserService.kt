package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.domain.dto.UserDTO

interface UserService {
    fun addUser(userDTO: UserDTO):String
}