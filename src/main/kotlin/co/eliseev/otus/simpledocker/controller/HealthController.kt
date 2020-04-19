package co.eliseev.otus.simpledocker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
@RequestMapping
class HealthController {

    @GetMapping
    fun checkHost() = "Hello from $hostName\n"

    @GetMapping("/health")
    fun healthCheck() = "version $VERSION from $hostName\n"

    companion object {
        private const val VERSION = 2
        private val hostName = InetAddress.getLocalHost().hostName
    }

}
