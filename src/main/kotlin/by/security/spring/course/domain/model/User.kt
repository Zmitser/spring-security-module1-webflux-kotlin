package by.security.spring.course.domain.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.util.*
import javax.validation.constraints.NotEmpty


@Document
data class User(
        @Id
        var id: ObjectId?,
//        @NotEmpty(message = "Email is required.")
        var email: String?,
//        @NotEmpty(message = "Password is required.")
        var password: String?,
        @Transient
//        @NotEmpty(message = "Password confirmation is required.")
        var passwordConfirmation: String?,
        var created: Calendar? = Calendar.getInstance()) : Serializable {
    constructor() : this(null, null, null, null, null)
    constructor(email: String?, password: String?, passwordConfirmation: String?) : this(null,  email, password, passwordConfirmation)
}