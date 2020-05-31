package co.eliseev.store.configuration

import co.eliseev.store.controller.UserController
import co.eliseev.store.model.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class InitApp: CommandLineRunner {

    @Autowired
    private lateinit var userController: UserController

    override fun run(vararg args: String?) {
        val userDto = UserDto(name = "INIT")
        val created = userController.create(userDto)
        userController.get(created.id!!)
        userController.getAll()
    }

}
