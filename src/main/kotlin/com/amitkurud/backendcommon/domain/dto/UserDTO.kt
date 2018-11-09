package com.amitkurud.backendcommon.domain.dto

import com.amitkurud.backendcommon.domain.models.User
import java.util.*

class UserDTO{
    constructor(user: User){
        this.username=user.username
        this.password=user.password
        this.firstName= user.userDetails!!.firstName
        this.middleName= user.userDetails!!.middleName
        this.lastName= user.userDetails!!.lastName
        this.email= user.userDetails!!.email
        this.phoneNumber= user.userDetails!!.phoneNumber
        this.birthdate= user.userDetails!!.birthdate
        this.country= user.userDetails!!.country
    }

    constructor()

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