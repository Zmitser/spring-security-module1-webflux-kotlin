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
        }
    }
}