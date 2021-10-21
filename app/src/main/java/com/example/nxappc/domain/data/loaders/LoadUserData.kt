package com.example.nxappc.domain.data.loaders

import com.example.nxappc.domain.database.entities.User
import com.example.nxappc.domain.database.entities.utils.UserResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun loadUserData(client: HttpClient): User {
    lateinit var userData: User
    lateinit var userResponse: UserResponse
    coroutineScope {
        launch { userResponse = client.get("https://reqres.in/api/users/${(1..12).random()}") }
    }
    userData = userResponse.data
    return userData
}