package com.example.sistemadegimnasio.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sistemadegimnasio.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
    fun login(email: String?, password: String?): User?

    @Query("SELECT COUNT(*) FROM Users")
    fun getUsersCount(): Int

    @Insert
    fun insertUser(user: User)
}