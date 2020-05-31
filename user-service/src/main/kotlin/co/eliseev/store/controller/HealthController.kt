package co.eliseev.store.controller

import io.micrometer.core.annotation.Timed
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

// TODO create starter
@RestController
@RequestMapping
class HealthController(private val env: Environment) {

    @GetMapping
    fun checkHost() = "Hello from $hostName\n"

    @GetMapping("/health")
    fun getVersion() = mapOf(STATUS to HttpStatus.OK)

    @Timed(value = "controller.health.version.requests")
    @GetMapping("/version")
    fun healthCheck() = "version $VERSION from $hostName\n"

    @GetMapping("/env")
    fun getEnv(@RequestParam("name") name: String) = env.getProperty(name)

    companion object {
        private const val STATUS = "status"
        private const val VERSION = 5
        private val hostName = InetAddress.getLocalHost().hostName
    }

}
