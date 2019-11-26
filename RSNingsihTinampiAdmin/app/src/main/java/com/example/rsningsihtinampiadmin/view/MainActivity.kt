package com.example.rsningsihtinampiadmin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.rsningsihtinampiadmin.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment2(R.id.frameLayout_1, LoginFragment())
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

    override fun onStart() {
        super.onStart()

        Log.d("rsningsihtinampiadmin", "MainActivity/18 : onStart")
    }

    override fun onRestart() {
        super.onRestart()

        Log.d("rsningsihtinampiadmin", "MainActivity/24 : onRestart")
    }

    override fun onPause() {
        super.onPause()

        Log.d("rsningsihtinampiadmin", "MainActivity/30 : onPause")
    }

    override fun onResume() {
        super.onResume()

        Log.d("rsningsihtinampiadmin", "MainActivity/36 : onResume")
    }

    override fun onStop() {
        super.onStop()

        Log.d("rsningsihtinampiadmin", "MainActivity/42 : onStop")
    }

    override fun onDestroy() {
        Log.d("rsningsihtinampiadmin", "MainActivity/46 : onDestroy")
        super.onDestroy()
    }
}
