package cz.hotelprice.security.hashing

data class SaltedHash(
    val hash: String,
    val salt: String
)
