package by.security.spring.course.web.router

import by.security.spring.course.web.handler.AccountHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED
import org.springframework.http.MediaType.TEXT_HTML
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

@Configuration
class AccountRouter(private val accountHandler: AccountHandler) {

    @Bean
    fun accountApi(): RouterFunction<ServerResponse> = router {
        ("/").nest {
            GET("/login") {
                contentType(TEXT_HTML)
                accountHandler.login(it)
            }
            GET("/signup") {
                contentType(TEXT_HTML)
                accountHandler.register(it)
            }
            POST("/user/register") {
                accept(APPLICATION_FORM_URLENCODED)
                contentType(APPLICATION_FORM_URLENCODED)
                accountHandler.save(it)
            }
        }
    }
}