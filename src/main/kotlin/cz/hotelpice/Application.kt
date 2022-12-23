package cz.hotelprice

import cz.hotelprice.plugins.*
import cz.hotelprice.security.hashing.SHA256HashingService
import cz.hotelprice.security.token.JwtTokenService
import cz.hotelprice.security.token.TokenConfig
import cz.hotelprice.data.MongoUserDataSource
import io.ktor.server.application.*
import org.litote.kmongo.KMongo


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val dbName = "HPdb"
    val db = KMongo.createClient()
        .getDatabase(dbName)
    val userDataSource = MongoUserDataSource(db)

//    val mongoPassword = System.getenv("MONGO_PASSWORD")
//    val dbName = "HPdb"
//    val db = KMongo.createClient(
//        connectionString = "mongodb+srv://endree:$mongoPassword@cluster0.ewagew.mongodb.net/$dbName?retryWrites=true&w=majority"
//    )
//        .coroutine
//        .getDatabase(dbName)

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
