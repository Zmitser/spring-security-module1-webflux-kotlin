package by.security.spring.course.web.handler

import by.security.spring.course.domain.model.User
import by.security.spring.course.domain.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.ServerResponse.temporaryRedirect
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.net.URI

@Component
class UserHandler(val userRepository: UserRepository) {


    fun list(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().render("/users/list", mapOf("users" to userRepository.findAll()))
    }

    fun view(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().render("/users/view", mapOf("user" to userRepository.findById(serverRequest.pathVariable("id"))))
    }

    fun delete(serverRequest: ServerRequest): Mono<ServerResponse> {
        return temporaryRedirect(URI.create("/")).build().doFirst { userRepository.deleteById(serverRequest.pathVariable("id")).subscribe() }

    }

    fun createForm(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().render("/users/form", mapOf("user" to User()))
    }

    fun modifyForm(serverRequest: ServerRequest): Mono<ServerResponse> {
        return ok().render("/users/form", mapOf("user" to userRepository.findById(serverRequest.pathVariable("id"))))
    }

    fun create(serverRequest: ServerRequest): Mono<ServerResponse> {
        return serverRequest.formData().map(this::convertToUser).flatMap {
            userRepository.save(it).subscribeOn(Schedulers.parallel()).flatMap { ok().render("/users/view", mapOf("user" to it)) }
        }
    }

    private fun convertToUser(it: MultiValueMap<String, String>): User {
        val userInfo = it.toSingleValueMap()
        val id: String? = if (userInfo["id"].isNullOrEmpty()) null else userInfo["id"]
        return User(id, userInfo["email"], userInfo["username"], userInfo["password"], userInfo["passwordConfirmation"])
    }
}