package com.example.rsningsihtinampiadmin.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rsningsihtinampiadmin.R
import com.example.rsningsihtinampiadmin.viewmodel.ResetPasswordViewModel

class ResetPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ResetPasswordFragment()
    }

    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reset_password_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ResetPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
