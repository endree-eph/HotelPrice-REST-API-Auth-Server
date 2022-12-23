package cz.hotelprice.plugins

import cz.hotelprice.data.UserDataSource
import cz.hotelprice.routes.getSecretInfo
import cz.hotelprice.routes.signIn
import cz.hotelprice.routes.signUp
import cz.hotelprice.security.hashing.HashingService
import cz.hotelprice.security.token.JwtTokenService
import cz.hotelprice.security.token.TokenConfig
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: JwtTokenService,
    tokenConfig: TokenConfig
) {

    routing {
        signUp(hashingService = hashingService, userDataSource = userDataSource)
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        getSecretInfo()
    }
}
