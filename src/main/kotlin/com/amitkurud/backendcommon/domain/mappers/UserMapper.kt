package com.amitkurud.backendcommon.domain.mappers

import com.amitkurud.backendcommon.domain.dto.UserDto
import com.amitkurud.backendcommon.domain.models.User

class UserMapper{
    fun userDtoToUser(userDto: UserDto): User?
    {
        return null
    }
    fun userToUserDto(user: User): UserDto?
    {
        return null
    }
}