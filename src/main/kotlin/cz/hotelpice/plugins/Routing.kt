package cz.hotelpice.plugins

import cz.hotelpice.data.UserDataSource
import cz.hotelpice.security.hashing.HashingService
import cz.hotelpice.security.token.JwtTokenService
import cz.hotelpice.security.token.TokenConfig
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: JwtTokenService,
    tokenConfig: TokenConfig
) {
    routing {

    }
}
