package com.amitkurud.backendcommon.domain.models

import org.jetbrains.annotations.NotNull
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "mst_user")
class User(){
  @Id
  @GeneratedValue @Column(name = "id")
  var id: Long? = null
  @Column(name = "username",unique = true)
  @NotNull
  @NotBlank
  @NotEmpty
  var username: String = ""
  @Column(name = "password")
  @NotNull
  @NotBlank
  @NotEmpty
  var password: String = ""
  @Column(name = "is_approved")
  var isApproved: Boolean = false
}

