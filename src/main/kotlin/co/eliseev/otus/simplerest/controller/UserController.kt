package co.eliseev.otus.simplerest.controller

import co.eliseev.otus.simplerest.model.converter.toDto
import co.eliseev.otus.simplerest.model.converter.toModel
import co.eliseev.otus.simplerest.model.dto.UserDto
import co.eliseev.otus.simplerest.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun create(@RequestBody userDto: UserDto): UserDto =
        userDto.toModel()
            .let { userService.create(it) }
            .toDto()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): UserDto =
        userService.get(id)
            .toModel()

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody userDto: UserDto
    ): UserDto =
        userDto.toModel()
            .let { userService.update(id, it) }
            .toDto()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Boolean = userService.delete(id)

    @GetMapping
    fun getAll(): List<UserDto> = userService.getAll()
        .toModel()

}
