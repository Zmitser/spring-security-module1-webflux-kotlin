package by.security.spring.course.web.handler

import by.security.spring.course.domain.model.User
import by.security.spring.course.domain.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.ServerResponse.temporaryRedirect
import reactor.core.publisher.Mono
import java.net.URI

@Component
class AccountHandler(val userRepository: UserRepository) {

    fun login(serverRequest: ServerRequest): Mono<ServerResponse> = ok().render("loginForm")

    fun register(serverRequest: ServerRequest): Mono<ServerResponse> = ok().render("registrationPage", mapOf("user" to User()))

    fun save(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.formData()
                .map {
                    User(it["email"]?.first(), it["password"]?.first(), it["passwordConfirmation"]?.first())
                }
                .map {
                    val save: Mono<User> = userRepository.save(it)
                    save
                }

                .flatMap {
                    temporaryRedirect(URI.create("/login")).build()
                }

    }
}