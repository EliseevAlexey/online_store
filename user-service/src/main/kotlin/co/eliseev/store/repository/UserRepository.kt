package co.eliseev.store.repository

import co.eliseev.store.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>
