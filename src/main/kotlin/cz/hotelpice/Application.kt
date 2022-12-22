package cz.hotelprice


import cz.hotelprice.plugins.*
import cz.hotelprice.security.hashing.SHA256HashingService
import cz.hotelprice.security.token.JwtTokenService
import cz.hotelprice.security.token.TokenConfig
import cz.hotelprice.data.MongoUserDataSource
import io.ktor.server.application.*
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val db = KMongo.createClient()
    val database = db.getDatabase("HPdb")
    //val HPdbCollection = database.getCollection<User>()

    val userDataSource = MongoUserDataSource(database)

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        exiresIn = 365L * 1000L * 60L * 24L,
        secret = System.getenv("JWT_SECRET")
            //?: throw Exception("JWT_SECRET environment variable not found - aborting")

    )

    val hashingService = SHA256HashingService()

    configureSecurity(tokenConfig)
    configureSerialization()
    configureMonitoring()
    configureRouting(
        userDataSource = userDataSource,
        hashingService = hashingService,
        tokenService = tokenService,
        tokenConfig = tokenConfig
    )
}
