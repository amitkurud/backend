package com.amitkurud.backendcommon.domain.models

import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "mst_userdetails")
class UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "userdetails_id")
    var id: Long? = null
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    var firstName = ""
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    var middleName = ""
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    var lastName = ""
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    var email = ""
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    var phoneNumber = ""
    @Column
    @NotNull
    var birthdate = Date()
    @Column
    @NotNull
    @NotBlank
    @NotEmpty
    var country = ""
}