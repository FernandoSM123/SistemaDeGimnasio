package com.example.sistemadegimnasio.viewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sistemadegimnasio.dao.DBConnection
import kotlinx.coroutines.launch

class LoginViewModel(private val context: Context) : ViewModel() {

    private val userDao = DBConnection.getConnection().userDao()

    fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val user = userDao.login(email, password)

            //almacenar el id del usuario en shared preferences
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("GymApp", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            if (user != null) {
                editor.putInt("userID", user.id)
            }
            editor.apply()

            callback(user != null)
        }
    }
}