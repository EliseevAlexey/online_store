package co.eliseev.otus.simplerest.service

import co.eliseev.otus.simplerest.model.UserModel
import co.eliseev.otus.simplerest.model.converter.toEntity
import co.eliseev.otus.simplerest.model.entity.User
import co.eliseev.otus.simplerest.repository.UserRepository
import org.slf4j.LoggerFactory
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

    override fun create(userModel: UserModel): User {
        logger.info("Saving user model $userModel")
        return userModel.toEntity()
            .apply { id = 0L }
            .let {
                logger.info("User $it")
                userRepository.save(it)
            }.also { logger.info("Saved user $it by $userModel") }
    }

    override fun get(id: Long): User {
        logger.info("Getting user by id $id")
        return userRepository.getOne(id)
            .also { logger.info("Found user $it by id $id") }
    }

    @Transactional
    override fun update(id: Long, userModel: UserModel): User {
        logger.info("Founding user by id $id")
        return get(id)
            .apply { name = userModel.name }
            .also { logger.info("Updated user $it by id $id, userModel $userModel") }
    }

    override fun delete(id: Long): Boolean {
        logger.info("Deleting user by id $id")
        userRepository.deleteById(id)
        return true
    }

    override fun getAll(): List<User> {
        logger.info("Getting all users")
        return userRepository.findAll()
            .also { logger.info("Found users $it") }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }

}
