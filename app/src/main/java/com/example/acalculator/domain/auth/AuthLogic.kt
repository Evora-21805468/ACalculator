package com.example.acalculator.domain.auth

import com.example.acalculator.data.remote.requests.Login
import com.example.acalculator.data.remote.services.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class AuthLogic (private val retrofit: Retrofit) {

    private val TAG = AuthLogic::class.java.simpleName

    fun authenticateUser(email: String, password: String) {
        val service = retrofit.create(AuthService::class.java)
        var login = false
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.login(Login(email, password))
            if (response.isSuccessful) {
                response.body()
            } else {
                println("Fodeu")
            }
        }
    }
}