package co.eliseev.store.controller


import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @PostMapping("/registration")
    fun registration() {}

    @PostMapping("/login")
    fun login() {}

}
