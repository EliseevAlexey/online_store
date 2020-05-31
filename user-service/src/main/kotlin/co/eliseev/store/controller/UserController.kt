package co.eliseev.store.controller

import co.eliseev.store.model.converter.toDto
import co.eliseev.store.model.UserDto
import co.eliseev.store.model.converter.toModel
import co.eliseev.store.service.UserService
import io.micrometer.core.annotation.Timed
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Timed(value = "controller.user.requests", histogram = true)
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(@Valid @RequestBody userDto: UserDto): UserDto =
        userDto.toModel()
            .let { userService.create(it) }
            .toDto()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): UserDto = userService.get(id).toDto()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody userDto: UserDto
    ): UserDto =
        userDto.toModel()
            .let { userService.update(id, it) }
            .toDto()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Boolean = userService.delete(id)

    @GetMapping
    fun getAll(): List<UserDto> = userService.getAll().toDto()

}
