package by.security.spring.course

import by.security.spring.course.domain.model.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import by.security.spring.course.domain.repository.InMemoryUserRepository
import by.security.spring.course.domain.repository.UserRepository

import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter
import org.springframework.web.reactive.config.EnableWebFlux
import reactor.core.publisher.Mono


@SpringBootApplication
class CourseApplication{

    @Bean
    fun userRepository(): UserRepository {
        return InMemoryUserRepository()
    }

}

fun main(args: Array<String>) {
    runApplication<CourseApplication>(*args)
}
