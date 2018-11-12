package com.amitkurud.backendcommon.services.implementations

import com.amitkurud.backendcommon.domain.dto.UserDTO
import com.amitkurud.backendcommon.domain.mappers.UserMapper
import com.amitkurud.backendcommon.domain.repository.UserRepository
import com.amitkurud.backendcommon.services.UserService
import org.apache.commons.codec.digest.DigestUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class UserServiceImpl(@Autowired var userRepository: UserRepository, @Autowired var userMapper: UserMapper) : UserService {
    val log = LoggerFactory.getLogger(this.javaClass)!!
    override fun addUser(userDTO: UserDTO): String {
        log.debug("adding user with username : {} and email : {}", userDTO.username, userDTO.email)
        userDTO.let {
            return if (userRepository.findByUsername(userDTO.username) != null) {
                log.debug("User with username : {} already exists", userDTO.username)
                "The User already Exists , Cannot register. If you have forgotten password Please use link \"forgot password\""
            } else {
                if (userDTO.username.length >= 256 || userDTO.password.length >= 256) {
                    log.debug("Username exceeds more than 256")
                    "Please Username or Password Can't exceed 256 chars "
                } else {
                    userDTO.password = DigestUtils.sha256Hex(userDTO.password)
                    userRepository.save(userMapper.userDTOToUser(userDTO)!!)
                    "User registered Successfully"
                }
            }
        }
    }
}