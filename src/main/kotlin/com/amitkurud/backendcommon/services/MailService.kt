package com.amitkurud.backendcommon.services

import com.amitkurud.backendcommon.domain.models.User

interface MailService {
    fun sendEmail(to: String, subject: String, content: String, isMultipart: Boolean, isHtml: Boolean)
    fun sendEmailFromTemplate(user: User, templateName: String, titleKey: String)
    fun sendActivationEmail(user: User)
    fun sendCreationEmail(user: User)
    fun sendPasswordResetMail(user: User)
}