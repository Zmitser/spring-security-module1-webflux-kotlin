package by.security.spring.course.domain.repository

import by.security.spring.course.domain.model.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono


interface UserRepository : ReactiveMongoRepository<User, String> {

    fun findByEmail(email: String?): Mono<User>

}