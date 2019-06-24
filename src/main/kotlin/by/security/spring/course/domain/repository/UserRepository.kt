package by.security.spring.course.domain.repository

import by.security.spring.course.domain.model.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


interface UserRepository {

    fun findAll(): Flux<User>

    fun save(user: User): User

    fun findUser(id: Long): Mono<User>?

    fun deleteUser(id: Long): Mono<User>?

}