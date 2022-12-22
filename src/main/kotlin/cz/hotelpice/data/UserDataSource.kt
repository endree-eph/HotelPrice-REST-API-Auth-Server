package cz.hotelpice.data

import cz.hotelpice.data.models.User

interface UserDataSource {
    suspend fun getUserByUsername(username: String): User?
    suspend fun insertUser(user: User): Boolean
}