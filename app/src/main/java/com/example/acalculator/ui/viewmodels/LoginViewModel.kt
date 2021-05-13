package com.example.acalculator.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.acalculator.data.remote.RetrofitBuilder
import com.example.acalculator.domain.auth.AuthLogic
import com.example.acalculator.ui.callback.login


const val ENDPOINT = "https://cm-calculadora.herokuapp.com/api/"

class LoginViewModel: ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))


    fun onClickButtonLogin(loginCallback: login, email: String, password: String){
        authLogic.authenticateUser(loginCallback, email,password)
    }


}