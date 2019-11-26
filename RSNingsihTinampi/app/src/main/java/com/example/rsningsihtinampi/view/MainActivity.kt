package com.example.rsningsihtinampi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.rsningsihtinampi.R
import com.example.rsningsihtinampi.jetpack.UserDatabase
import com.example.rsningsihtinampi.jetpack.UserRepository
import com.example.rsningsihtinampi.jetpack.Webservice

class MainActivity : AppCompatActivity() {
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userRepository = UserRepository(
            Webservice.create("http://13.250.104.101:8080/"),
            Room.databaseBuilder(this, UserDatabase::class.java, "rs_ningsih_tinampi").build().userDao())

        changeFragment2(R.id.frameLayout_1, LoginFragment())

        Log.d("rsningsihtinampi", "MainActivity/14 : onCreate")
    }

    fun changeFragment2(layout: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(layout, fragment)
            .commitAllowingStateLoss()

    }

    fun changeFragment3(layout: Int, fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(layout, fragment)
            .commitAllowingStateLoss()

    }

    fun popUp(message: String?){
        // LENGTH_SHORT itu berarti popUp nya muncul sebentar, kalau mau lama ganti dengan LENGTH_LONG
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }

    override fun onStart() {
        super.onStart()

        Log.d("rsningsihtinampi", "MainActivity/20 : onStart")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("rsningsihtinampi", "MainActivity/26 : onRestart")
    }

    override fun onPause() {
        super.onPause()

        Log.d("rsningsihtinampi", "MainActivity/31 : onPause")
    }

    override fun onResume() {
        super.onResume()

        Log.d("rsningsihtinampi", "MainActivity/38 : onResume")
    }

    override fun onStop() {
        super.onStop()

        Log.d("rsningsihtinampi", "MainActivity/44 : onStop")
    }

    override fun onDestroy() {
        Log.d("rsningsihtinampi", "MainActivity/50 : onDestroy")
        super.onDestroy()
    }
}
