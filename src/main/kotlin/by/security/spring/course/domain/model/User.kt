package by.security.spring.course.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty


@Document
data class User(
        @Id
        var id: String?,
        @NotEmpty(message = "Email is required.")
        var email: String?,
        @NotEmpty(message = "Password is required.")
        var username: String?,
        @NotEmpty(message = "Password is required.")
        var password: String?,
        @NotEmpty(message = "Password confirmation is required.")
        var passwordConfirmation: String?,
        @CreatedDate
        var created: LocalDateTime? = LocalDateTime.now()
//        var enabled: Boolean
) : Serializable {
    constructor() : this(null, null, null, null, null)
    constructor(email: String?, username: String?, password: String?, passwordConfirmation: String?) : this(null, email, username, password, passwordConfirmation)
}