package co.eliseev.store.model

import org.springframework.validation.annotation.Validated
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Validated
data class UserDto(
    val id: Long? = null,
    @NotEmpty val name: String? = null
)

data class UserModel(
    val id: Long? = null,
    val name: String? = null
)

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null
)
