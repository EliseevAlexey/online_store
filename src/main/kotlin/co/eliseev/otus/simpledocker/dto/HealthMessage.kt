package co.eliseev.otus.simpledocker.dto

import org.springframework.http.HttpStatus

data class HealthMessage(val status: HttpStatus)
