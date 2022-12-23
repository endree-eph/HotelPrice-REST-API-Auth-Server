package cz.hotelprice.data.models.requests

import com.typesafe.config.Optional
import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String,
    @Optional
    val hotelCountry: String = "",
    val hotelName: String = "",
    val hotelStars: String = ""
)