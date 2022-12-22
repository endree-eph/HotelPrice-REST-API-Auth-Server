package cz.hotelpice

import cz.hotelpice.data.MongoUserDataSource
import cz.hotelpice.data.models.User
import io.ktor.server.application.*
import cz.hotelpice.plugins.*
import cz.hotelpice.secure.JwtTokenService
import cz.hotelpice.secure.TokenConfig
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val dbName = "HPdb"
    val db = KMongo.createClient()
    val database = db.getDatabase("$dbName")
    val HPdbCollection = database.getCollection<User>()

    val userDataSource = MongoUserDataSource(database)

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        exiresIn = 365L * 1000L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
    )
    configureSecurity()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
