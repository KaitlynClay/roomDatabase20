package com.example.roomdatabase20.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabase20.data.UserDatabase
import com.example.roomdatabase20.repo.UserRepo
import com.example.roomdatabase20.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel's role is to provide data to the UI and survive configuration changes.
// Acts as a communication center between the Repo and the UI
class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepo

    init {
        val userDao = UserDatabase.getDataBase(application).userDao()
        repository = UserRepo(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

}