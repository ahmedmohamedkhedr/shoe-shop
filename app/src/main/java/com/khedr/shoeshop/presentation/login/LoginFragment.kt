package com.khedr.shoeshop.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.khedr.shoeshop.R
import com.khedr.shoeshop.databinding.FragmentLoginBinding
import com.khedr.shoeshop.presentation.MainActivity
import com.khedr.shoeshop.utils.navigateTo

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel by lazy {
        (requireActivity() as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupControllers()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.loginSuccessLiveData.observe(viewLifecycleOwner) {
            if (it) {
                //to on boarding
                navigateTo(LoginFragmentDirections.actionLoginFragmentToOnBoardFragment())
            }
        }
    }

    private fun setupControllers() {
        with(binding) {
            loginBtn.setOnClickListener {
                login()
            }

            existingLoginBtn.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()
        if (viewModel.isEmailValid(email) && viewModel.isPasswordValid(password)
        ) {
            viewModel.login(email, password)
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.email_or_password_not_valid),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}