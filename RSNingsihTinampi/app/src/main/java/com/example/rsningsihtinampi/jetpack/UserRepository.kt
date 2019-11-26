package com.example.rsningsihtinampi.jetpack

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rsningsihtinampi.model.LoginResponse
import com.example.rsningsihtinampi.model.Other
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executor

class UserRepository(private val webservice: Webservice, private val userDao: UserDao) {
    fun getData(name: String): LiveData<Other> { return userDao.readData(name) }

    fun login (body: RequestBody, loginListener: MutableLiveData<LoginResponse> ) {
        webservice.login(body).enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                val debug1 = t.message
                Log.d("rsningsihtinampi", "UserRepository/20 : ${debug1.toString()}")
                loginListener.value = LoginResponse("Login error")

            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginListener.value = LoginResponse(response.body()!!.Response)

            }


        })

    }
}