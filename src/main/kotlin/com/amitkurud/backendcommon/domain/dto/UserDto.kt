package com.amitkurud.backendcommon.domain.dto

import java.util.*

class UserDto{
    var username: String = ""
    var password: String = ""
    var id: Long? = null
    var firstName = ""
    var middleName = ""
    var lastName = ""
    var email = ""
    var phoneNumber = ""
    var birthdate = Date()
    var country = ""
}