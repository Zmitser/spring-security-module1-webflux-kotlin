package by.security.spring.course.domain.repository


import by.security.spring.course.domain.model.User
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.atomic.AtomicLong

class InMemoryUserRepository(
        private val  counter: AtomicLong =  AtomicLong(),
        private val users: ConcurrentMap<Long, User> = ConcurrentHashMap<Long, User>()
) : UserRepository {

    override fun findAll(): Flux<User> = this.users.values.toFlux()

    override fun save(user: User): User {
        user.id = user.id ?: counter.incrementAndGet()
        this.users[user.id] = user
        return user
       }


    override fun findUser(id: Long): Mono<User>? = this.users[id]?.toMono()

    override fun deleteUser(id: Long) = this.users.remove(id)?.toMono()
}