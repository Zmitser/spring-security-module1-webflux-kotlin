package by.security.spring.course.web.router

import by.security.spring.course.web.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router


@Configuration
class UserRouter(val userHandler: UserHandler) {
    @Bean
    fun userApi(): RouterFunction<ServerResponse> = router {
        ("/").nest {
            GET("/") {
                contentType(MediaType.TEXT_HTML)
                userHandler.list(it)
            }
            GET("/users/{id}") {
                contentType(MediaType.TEXT_HTML)
                userHandler.view(it)
            }
            GET("/create/form") {
                contentType(MediaType.TEXT_HTML)
                userHandler.createForm(it)
            }
            GET("/delete/{id}") {
                contentType(MediaType.TEXT_HTML)
                userHandler.delete(it)
            }
            GET("/modify/{id}"){
                contentType(MediaType.TEXT_HTML)
                userHandler.modifyForm(it)
            }
            POST("/save") {
                accept(MediaType.APPLICATION_FORM_URLENCODED)
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
                userHandler.create(it)
            }
        }
    }
}