package co.eliseev.store.model.converter

import co.eliseev.store.model.User
import co.eliseev.store.model.UserModel
import co.eliseev.store.model.UserDto

fun User.toDto() = UserDto(id = this.id, name = this.name)

fun List<User>.toDto(): List<UserDto> = this.map { it.toDto() }

fun UserDto.toModel() = UserModel(id = this.id, name = this.name)

fun UserModel.toEntity() = User(id = this.id, name = this.name)
