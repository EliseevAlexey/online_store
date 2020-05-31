package co.eliseev.store.configuration.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@Aspect
class LoggingAspect {

    @Around("execution(* co.eliseev.store..*(..))")
    fun loggingBefore(joinPoint: ProceedingJoinPoint): Any? {
        val args = getArgs(joinPoint)
        val absolutePath = getAbsolutePath(joinPoint)
        logger.info("CALL   : $absolutePath($args)")
        val result = joinPoint.proceed()
        logger.info("RESULT : $absolutePath $result")
        return result
    }

    private fun getAbsolutePath(joinPoint: ProceedingJoinPoint) =
        "${joinPoint.target.toString().split("@")[0]}#${joinPoint.signature.name}" // FIXME split

    private fun getArgs(joinPoint: ProceedingJoinPoint): String {
        val args = joinPoint.args
        return when {
            args.isEmpty() -> ""
            args.size == 1 -> args[0].toString()
            else -> args.toList().toString()
        }
    }

    private val logger = LoggerFactory.getLogger(this::class.java)
}

