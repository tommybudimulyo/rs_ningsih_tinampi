package com.example.rsningsihtinampi.view

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.rsningsihtinampi.R
import com.example.rsningsihtinampi.viewmodel.LoginViewModel
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
        viewModel.init((activity as MainActivity).userRepository)

        viewModel.loginListener().observe(this, Observer {
            if (it != null) {
                if (it.Response != null) {
                    // Kata "Login success" itu, logic yang didapat dari server, jadi tergantung servernya buat gimana
                    if (it.Response == "Login success") {
//                        val intent = Intent(context, HomeActivity::class.java)
//                        startActivity(intent)
//                        (activity as MainActivity).finish()

                        (activity as MainActivity).changeFragment2(R.id.frameLayout_1, SuccessFragment())

                    } else {
                        val mResponse = it.Response
                        (activity as MainActivity).popUp(mResponse)

                    }

                } else {
                    (activity as MainActivity).popUp("Responnya error gan")

                }

                viewModel.deactiveLoginListener()

            }

        })

        textView_login_fragment_6.setOnClickListener {
            (activity as MainActivity).changeFragment3(R.id.frameLayout_1, ResetPasswordFragment())
        }

        textView_login_fragment_8.setOnClickListener {
            (activity as MainActivity).changeFragment3(R.id.frameLayout_1, RegisterFragment())

        }
        button_login_fragment_7.setOnClickListener {
            viewModel.login(editText_login_fragment_3.text.toString(),editTex_login_fragment_5.text.toString())
//            val intent = Intent(context, HomeActivity::class.java)
//            startActivity(intent)
//            (activity as MainActivity).finish()



        }
    }

}
