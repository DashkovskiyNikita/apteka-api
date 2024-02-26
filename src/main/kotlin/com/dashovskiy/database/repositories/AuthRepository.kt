package com.dashovskiy.database.repositories

import com.dashovskiy.database.tables.User
import com.dashovskiy.database.tables.UserDAO
import com.dashovskiy.routes.auth.models.Register
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface AuthRepository {
    suspend fun login(login: String, password: String): UserDAO
    suspend fun register(register: Register): UserDAO
    suspend fun updateUser(userId: Int, register: Register): UserDAO
    suspend fun getUser(userId: Int): UserDAO
    suspend fun deleteUser(userId: Int)
}

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(login: String, password: String): UserDAO {
        return newSuspendedTransaction {
            UserDAO.find {
                ((User.email eq login) or (User.phone eq login)) and (User.password eq password)
            }.first()
        }
    }

    override suspend fun register(register: Register): UserDAO {
        return newSuspendedTransaction {
            UserDAO.new {
                name = register.name
                surname = register.surname
                phone = register.phone
                email = register.email
                password = register.password
            }
        }
    }

    override suspend fun updateUser(userId: Int, register: Register): UserDAO {
        return newSuspendedTransaction {
            UserDAO[userId].apply {
                name = register.name
                surname = register.surname
                phone = register.phone
                email = register.email
                password = register.password
            }
        }
    }

    override suspend fun getUser(userId: Int): UserDAO {
        return newSuspendedTransaction { UserDAO[userId] }
    }

    override suspend fun deleteUser(userId: Int) {
        newSuspendedTransaction { UserDAO[userId].delete() }
    }

}