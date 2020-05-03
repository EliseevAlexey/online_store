package co.eliseev.otus.simplerest.repository

import co.eliseev.otus.simplerest.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>
