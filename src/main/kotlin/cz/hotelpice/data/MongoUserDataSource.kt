package cz.hotelpice.data

import com.mongodb.client.MongoDatabase
import cz.hotelpice.data.models.User
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class MongoUserDataSource(
    db: MongoDatabase
): UserDataSource {
    private val users = db.getCollection<User>()
    override suspend fun getUserByUsername(username: String): User? =
        users.findOne(User::username eq username)

    override suspend fun insertUser(user: User): Boolean =
        users.insertOne(user).wasAcknowledged()
}