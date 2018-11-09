package com.amitkurud.backendcommon.domain.repository

import com.amitkurud.backendcommon.domain.models.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDetailsRepository : JpaRepository<UserDetails,Long>