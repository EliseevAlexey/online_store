package co.eliseev.store.service

import co.eliseev.store.model.UserModel
import co.eliseev.store.model.converter.toEntity
import co.eliseev.store.model.User
import co.eliseev.store.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface UserService {
    fun create(userModel: UserModel): User
    fun get(id: Long): User
    fun update(id: Long, userModel: UserModel): User
    fun delete(id: Long): Boolean
    fun getAll(): List<User>
}

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun create(userModel: UserModel): User =
        userModel.toEntity()
            .apply { id = 0L }
            .let { userRepository.save(it) }

    override fun get(id: Long): User = userRepository.getOne(id)

    @Transactional
    override fun update(id: Long, userModel: UserModel): User =
        get(id)
            .apply { name = userModel.name }

    override fun delete(id: Long): Boolean {
        userRepository.deleteById(id)
        return true
    }

    override fun getAll(): List<User> = userRepository.findAll()

}
