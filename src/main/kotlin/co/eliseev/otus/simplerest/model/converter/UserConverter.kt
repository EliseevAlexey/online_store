package co.eliseev.otus.simplerest.model.converter

import co.eliseev.otus.simplerest.model.UserModel
import co.eliseev.otus.simplerest.model.dto.UserDto
import co.eliseev.otus.simplerest.model.entity.User

fun User.toModel() = UserDto(id = this.id, name = this.name)

fun List<User>.toModel() = this.map { it.toModel() }

fun UserDto.toModel() = UserModel(id = this.id, name = this.name)

fun UserModel.toEntity() = User(id = this.id, name = this.name)

fun User.toDto() = UserDto(id = this.id, name = this.name)
