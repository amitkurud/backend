package com.amitkurud.backendcommon.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver

import java.util.concurrent.TimeUnit

@Configuration
@EnableWebMvc
class MvcWebConfig(@Autowired
                        private val applicationContext: ApplicationContext) : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {

        // Register resource handler for CSS and JS
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/", "/webapp/statics")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())


        // Register resource handler for images
        registry.addResourceHandler("/images/**").addResourceLocations("/webapp/images/")
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic())
    }

    @Bean
    fun templateResolver(): SpringResourceTemplateResolver {
        val templateResolver = SpringResourceTemplateResolver()
        templateResolver.setApplicationContext(applicationContext)
        templateResolver.prefix = "/webapp/"
        templateResolver.suffix = ".html"
        return templateResolver
    }

    /*
   * STEP 2 - Create SpringTemplateEngine
   * */
    @Bean
    fun templateEngine(): SpringTemplateEngine {
        val templateEngine = SpringTemplateEngine()
        templateEngine.setTemplateResolver(templateResolver())
        templateEngine.enableSpringELCompiler = true
        return templateEngine
    }

    /*
   * STEP 3 - Register ThymeleafViewResolver
   * */
    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        val resolver = ThymeleafViewResolver()
        resolver.templateEngine = templateEngine()
        registry.viewResolver(resolver)
    }
}