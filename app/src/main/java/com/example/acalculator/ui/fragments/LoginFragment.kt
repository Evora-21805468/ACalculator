package com.example.acalculator.ui.fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.acalculator.R
import com.example.acalculator.ui.callback.login
import com.example.acalculator.ui.utils.NavigationManager
import com.example.acalculator.ui.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), login {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    @OnClick(R.id.login)
    fun onClickLogin(view: View) {
        Log.i("Login", username.text.toString())
        Log.i("Password", password.text.toString())
        viewModel.onClickButtonLogin(this, username.text.toString(), password.text.toString())

    }

    override fun OnLogin() {
        activity?.let {
            NavigationManager.goToCalculatorFragment(
                it.supportFragmentManager
            )

        }

    }
}