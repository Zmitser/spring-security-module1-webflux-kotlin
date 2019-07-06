package by.security.spring.course.domain.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
class VerificationToken(
        @Id
        var id: String,
        var token: String,
        @DBRef
        var user: User,
        var expirationDate: LocalDate
)