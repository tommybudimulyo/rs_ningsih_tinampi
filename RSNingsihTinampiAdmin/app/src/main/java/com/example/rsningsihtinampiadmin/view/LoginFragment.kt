package com.example.rsningsihtinampiadmin.view

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rsningsihtinampiadmin.R
import com.example.rsningsihtinampiadmin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        textView_login_fragment_6.setOnClickListener {
            (activity as MainActivity).changeFragment3(R.id.frameLayout_1, ResetPasswordFragment())
        }

        textView_login_fragment_8.setOnClickListener {
            (activity as MainActivity).changeFragment3(R.id.frameLayout_1, RegisterFragment())

        }
        button_login_fragment_7.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java)
            startActivity(intent)
            (activity as MainActivity).finish()

        }
    }

}
