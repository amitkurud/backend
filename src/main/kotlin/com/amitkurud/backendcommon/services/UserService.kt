package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.domain.dto.UserDTO
import com.amitkurud.backendcommon.domain.mappers.UserMapper
import com.amitkurud.backendcommon.domain.repository.UserRepository
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired
                  var userRepository: UserRepository,@Autowired var userMapper: UserMapper) {
    fun addUser(userDTO: UserDTO): String {
        userDTO.let {
            return if (userRepository.findByUsername(userDTO.username) != null) {
                "The User already Exists , Cannot register. If you have forgotten password Please use link \"forgot password\""
            } else {
                if (userDTO.username.length >= 256 || userDTO.password.length >= 256) {
                    "Please Username or Password Can't exeed 256 chars "
                } else {
                    userDTO.password=DigestUtils.sha256Hex(userDTO.password)
                    userRepository.save(userMapper.userDTOToUser(userDTO)!!)
                    "User registered Successfully"
                }
            }
        }
    }
}