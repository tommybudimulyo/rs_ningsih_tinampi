package com.example.rsningsihtinampi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rsningsihtinampi.jetpack.UserRepository
import com.example.rsningsihtinampi.model.LoginResponse
import okhttp3.MultipartBody
import org.jetbrains.anko.doAsync

class LoginViewModel : ViewModel() {
    // 1
    private var userRepository: UserRepository? = null
    // 2
    private var loginListener = MutableLiveData<LoginResponse>()

    fun init(userRepository: UserRepository) { this.userRepository = userRepository }

    fun login(email: String, password: String) {
        doAsync {
            val body = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email) // Email itu dapetnya dari API yang udah dibuat server
                .addFormDataPart("password", password) // Password ini juga dapetnya dari API yang udah dibuat server
                .build()

            // Tanda seru dua kali itu artinya variable itu ga boleh null, klo null bakalan error
            userRepository!!.login(body, loginListener)

        }

    }

    fun deactiveLoginListener() { loginListener.value = null }

    fun loginListener(): LiveData<LoginResponse> { return loginListener }

}
