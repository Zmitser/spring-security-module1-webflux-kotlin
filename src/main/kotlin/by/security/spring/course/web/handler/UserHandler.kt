package by.security.spring.course.web.handler

import by.security.spring.course.domain.model.User
import by.security.spring.course.domain.repository.UserRepository

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono

@Component
class UserHandler(val userRepository: UserRepository) {


    fun list(serverRequest: ServerRequest): Mono<ServerResponse> = ok().render("/users/list", mapOf("users" to userRepository.findAll()))
}