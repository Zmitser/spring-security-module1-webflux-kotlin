package by.security.spring.course.domain.model

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import javax.validation.constraints.NotEmpty


@Document
data class User(var id: Long?,
                @NotEmpty(message = "Username is required.")
                var username: String?,
                @NotEmpty(message = "Email is required.")
                var email: String?,
                @NotEmpty(message = "Password is required.")
                var password: String?,
                @Transient
                @NotEmpty(message = "Password confirmation is required.")
                var passwordConfirmation: String?,
                var created: Calendar? = Calendar.getInstance()) {
    constructor() : this(null, null, null, null, null)
}