package com.dashovskiy.database

import com.dashovskiy.controllers.auth.models.Register
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

interface AuthRepository {
    suspend fun login(login: String, password: String): UserDAO
    suspend fun register(register: Register): UserDAO
    suspend fun updateUser(userId: Int, register: Register): UserDAO
    suspend fun deleteUser(userId: Int)
}

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(login: String, password: String): UserDAO {
        return newSuspendedTransaction {
            UserDAO.find {
                (User.email eq login) or (User.phone eq login)
            }.firstOrNull() ?: throw Exception("No user with specified login")
        }
    }

    override suspend fun register(register: Register): UserDAO {
        return newSuspendedTransaction {
            val phoneAndNumberAreUnique = UserDAO.find {
                (User.email eq register.email) or (User.phone eq register.phone)
            }.empty()
            if (!phoneAndNumberAreUnique) {
                throw Exception("User with specified email or phone already exists")
            }
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
            val user = UserDAO.findById(id = userId)
                ?: throw Exception("User with specified id not found")
            user.apply {
                name = register.name
                surname = register.surname
                phone = register.phone
                email = register.email
                password = register.password
            }
        }
    }

    override suspend fun deleteUser(userId: Int) {
        newSuspendedTransaction {
            UserDAO.findById(id = userId)?.delete()
                ?: throw Exception("User with specified id not found")
        }
    }

}