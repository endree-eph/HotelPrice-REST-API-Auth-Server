package cz.hotelprice.data

import cz.hotelprice.data.models.User

interface UserDataSource {
    suspend fun getUserByUsername(username: String): User?
    suspend fun insertUser(user: User): Boolean
}