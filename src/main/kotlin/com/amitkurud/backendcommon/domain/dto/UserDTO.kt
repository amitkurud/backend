package com.amitkurud.backendcommon.domain.dto

import com.amitkurud.backendcommon.domain.models.User
import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.validation.constraints.*

class UserDTO {
    constructor(user: User) {
        this.username = user.username
        this.password = user.password
        this.firstName = user.userDetails!!.firstName
        this.middleName = user.userDetails!!.middleName
        this.lastName = user.userDetails!!.lastName
        this.email = user.userDetails!!.email
        this.phoneNumber = user.userDetails!!.phoneNumber
        this.birthDate = user.userDetails!!.birthdate
        this.country = user.userDetails!!.country
    }

    constructor()

    //var id: Long? = null

    @NotBlank
    @NotEmpty
    var username: String = ""
    @NotBlank
    @NotEmpty
    var password: String = ""
    @NotBlank
    @NotEmpty
    var firstName = ""
    @NotBlank
    @NotEmpty
    var middleName = ""
    @NotBlank
    @NotEmpty
    var lastName = ""
    @NotBlank
    @NotEmpty
    @Email
    var email = ""
    @Size(min = 8, max = 12)
    var phoneNumber = ""
    @Future
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    var birthDate = Date()
    @NotBlank
    @NotEmpty
    var country = ""
}