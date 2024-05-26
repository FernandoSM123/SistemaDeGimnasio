package com.example.sistemadegimnasio.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemadegimnasio.dao.AppDatabase
import com.example.sistemadegimnasio.dao.DBConnection
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    private val userDao = DBConnection.getConnection().userDao()

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.login(email, password)
            callback(user != null)
        }
    }
}