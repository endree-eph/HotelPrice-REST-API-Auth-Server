package cz.hotelpice

import io.ktor.server.application.*
import cz.hotelpice.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSecurity()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
