package cz.hotelprice.data.models.requests

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val username: String,
    val password: String,
    val hotelCountry: String,
    val hotelName: String,
    val hotelStars: Int,
)