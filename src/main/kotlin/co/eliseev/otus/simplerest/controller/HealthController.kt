package co.eliseev.otus.simplerest.controller


import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
@RequestMapping
class HealthController(private val env: Environment) {

    @GetMapping
    fun checkHost() = "Hello from $hostName\n"

    @GetMapping("/health")
    fun getVersion() = mapOf(STATUS to HttpStatus.OK)

    @GetMapping("/version")
    fun healthCheck() = "version $VERSION from $hostName\n"

    @GetMapping("/env")
    fun getEnv(@RequestParam("name") name: String) = env.getProperty(name)

    companion object {
        private const val STATUS = "status"
        private const val VERSION = 3
        private val hostName = InetAddress.getLocalHost().hostName
    }

}
