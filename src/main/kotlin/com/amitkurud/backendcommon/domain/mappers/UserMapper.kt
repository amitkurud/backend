package com.amitkurud.backendcommon.domain.mappers

import com.amitkurud.backendcommon.domain.dto.UserDTO
import com.amitkurud.backendcommon.domain.models.User
import com.amitkurud.backendcommon.domain.models.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserMapper {

    fun userToUserDTO(user: User): UserDTO {
        return UserDTO(user)
    }

    fun usersToUserDTOs(users: List<User>): List<UserDTO> {
        return users
                .filter { Objects.nonNull(it) }
                .map { this.userToUserDTO(it) }
    }

    fun userDTOToUser(userDTO: UserDTO): User? {
        var user = User()
        user.username = userDTO.username
        user.password = userDTO.password
        user.userDetails = UserDetails()
        user.userDetails!!.firstName = userDTO.firstName
        user.userDetails!!.middleName = userDTO.middleName
        user.userDetails!!.lastName = userDTO.lastName
        user.userDetails!!.email = userDTO.email
        user.userDetails!!.country = userDTO.country
        user.userDetails!!.phoneNumber = userDTO.phoneNumber
        user.userDetails!!.birthdate = userDTO.birthdate
        return user
    }
    fun userDTOsToUsers(userDTOs: List<UserDTO>): List<User?> {
        return userDTOs
                .filter{ Objects.nonNull(it) }
                .map{ this.userDTOToUser(it) }
    }

    fun userFromId(id: Long?): User? {
        if (id == null) {
            return null
        }
        val user = User()
        user.id = id
        return user
    }


}