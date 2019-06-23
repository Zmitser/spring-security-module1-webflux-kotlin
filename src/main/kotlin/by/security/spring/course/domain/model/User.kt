package by.security.spring.course.domain.model

import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotEmpty


data class User(var id: Long?,
                @NotEmpty(message = "Username is required.")
                var username: String?,
                @NotEmpty(message = "Email is required.")
                var email: String?,
                var created: Calendar? = Calendar.getInstance())