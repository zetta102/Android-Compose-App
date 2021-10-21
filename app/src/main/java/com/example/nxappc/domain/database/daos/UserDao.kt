package com.example.nxappc.domain.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nxappc.domain.database.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User): Long

    @Query("SELECT * FROM users")
    fun getUser(): User
}