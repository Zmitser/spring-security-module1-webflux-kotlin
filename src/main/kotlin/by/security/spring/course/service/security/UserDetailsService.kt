package by.security.spring.course.service.security

import by.security.spring.course.constant.Constant.ROLE_USER
import by.security.spring.course.domain.model.User
import by.security.spring.course.domain.repository.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.Locale.ENGLISH

@Service
class UserDetailsService(private val userRepository: UserRepository) : ReactiveUserDetailsService {




    override fun findByUsername(username: String?): Mono<UserDetails> {
        val lowerCaseLogin: String? = username?.toLowerCase(ENGLISH)
        return userRepository.findByEmail(lowerCaseLogin).map { createSecurityUser(it) }
    }


    private fun createSecurityUser(user: User): org.springframework.security.core.userdetails.User {
        return org.springframework.security.core.userdetails.User(user.email, user.password, listOf(SimpleGrantedAuthority(ROLE_USER)))

    }
}