package com.amitkurud.backendcommon.services.implementations

import com.amitkurud.backendcommon.domain.models.User
import com.amitkurud.backendcommon.services.MailService
import org.springframework.context.MessageSource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.thymeleaf.spring5.SpringTemplateEngine

@Service
class MailServiceImpl(private val javaMailSender: JavaMailSender,
                      private val messageSource: MessageSource, private val templateEngine: SpringTemplateEngine) : MailService {
    override fun sendEmail(to: String, subject: String, content: String, isMultipart: Boolean, isHtml: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendEmailFromTemplate(user: User, templateName: String, titleKey: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendActivationEmail(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendCreationEmail(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendPasswordResetMail(user: User) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}