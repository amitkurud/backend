package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.domain.models.User
import com.amitkurud.backendcommon.domain.repository.UserRepository
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class UserService(@Autowired
                  var userRepository: UserRepository) {
    fun addUser(user: User): String {
        user.let {
            return if (userRepository.findByUsername(user.username) != null) {
                "The User already Exists , Cannot register. If you have forgotten password Please use link \"forgot password\""
            } else {
                if (user.username.length >= 256 || user.password.length >= 256) {
                    "Please Username or Password Can't exeed 256 chars "
                } else {
                    user.password=DigestUtils.sha256Hex(user.password)
                    userRepository.save(user)
                    "User registered Successfully"
                }
            }
        }
    }
}