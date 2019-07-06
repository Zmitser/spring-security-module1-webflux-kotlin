package by.security.spring.course.domain.repository

import by.security.spring.course.domain.model.VerificationToken
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface VerificationTokenRepository : ReactiveMongoRepository<VerificationToken, String> {

    fun findByToken(token:String): Mono<VerificationToken>
}