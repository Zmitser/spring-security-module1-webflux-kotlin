package by.security.spring.course

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import by.security.spring.course.domain.repository.UserRepository

import org.springframework.context.annotation.Bean


@SpringBootApplication
class CourseApplication

fun main(args: Array<String>) {
    runApplication<CourseApplication>(*args)
}
