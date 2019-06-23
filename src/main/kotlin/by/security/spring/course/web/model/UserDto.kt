package by.security.spring.course.web.model

import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

data class UserDto(var id: Long?,
                @NotEmpty(message = "Username is required.")
                var username: String?,
                @NotEmpty(message = "Email is required.")
                var email: String?,
                var created: LocalDateTime? = LocalDateTime.now())