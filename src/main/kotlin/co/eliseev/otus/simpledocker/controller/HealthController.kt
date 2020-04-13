package co.eliseev.otus.simpledocker.controller

import co.eliseev.otus.simpledocker.dto.HealthMessage
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController {

    @GetMapping
    fun healthCheck() = HealthMessage(HttpStatus.OK)
}
