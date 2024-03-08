package com.example.roomdatabase20.repo

import androidx.lifecycle.LiveData
import com.example.roomdatabase20.data.UserDao
import com.example.roomdatabase20.model.User

// Repository class abstracts access to multiple data sources: suggested for best practice but not mandatory
class UserRepo(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }
}