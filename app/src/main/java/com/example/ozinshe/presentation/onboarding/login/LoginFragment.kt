package com.example.ozinshe.presentation.onboarding.login

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.data.SharedProvider
import com.example.ozinshe.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            SharedProvider(requireContext()).saveToken(it.accessToken)
            saveAccessToken(viewModel.loginResponse.value!!.accessToken)
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            showError(it)
        }

        binding.btnBackLoginFragment.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnShowPassword.setOnClickListener {
            togglePasswordVisibility()
        }


        binding.btnLogin.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            val emailIsValid = emailValidation(email)
            val passwordIsValid = validationPassword(password)

            if (emailIsValid && passwordIsValid) {
                viewModel.loginUser(email, password)
            } else {
                validationEmail(emailIsValid)
            }
        }
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }


    private fun togglePasswordVisibility() {
        val passwordEditText = binding.editTextPassword
        passwordEditText.transformationMethod =
            if (passwordEditText.transformationMethod == HideReturnsTransformationMethod.getInstance()) {
                PasswordTransformationMethod.getInstance()
            } else {
                HideReturnsTransformationMethod.getInstance()
            }
    }

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private fun emailValidation(email: String): Boolean {
        return email.trim().matches(emailPattern.toRegex())
    }

    private fun validationEmail(isValid: Boolean) {
        if (isValid) {
            binding.tvErrorEmail.text = ""
            binding.tvErrorEmail.visibility = View.GONE
            binding.editTextEmail.setBackgroundResource(R.drawable.edittext_focus_action_12dp)
        } else {
            binding.tvErrorEmail.text = "Қате формат"
            binding.tvErrorEmail.visibility = View.VISIBLE
            binding.editTextEmail.setBackgroundResource(R.drawable.background_edittext_12dp_error)
        }
    }

    private fun validationPassword(password: String): Boolean {
        return if (password.length < 6) {
            binding.tvErrorTextPasswordAndServer.text = "Құпия сөз 6 таңбадан кем болмауы керек"
            binding.editTextPassword.setBackgroundResource(R.drawable.background_edittext_12dp_error)
            binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
            false
        } else {
            binding.tvErrorTextPasswordAndServer.visibility = View.GONE
            binding.editTextPassword.setBackgroundResource(R.drawable.edittext_focus_action_12dp)
            true
        }
    }

    private fun showError(message: String) {
        binding.tvErrorTextPasswordAndServer.text = message
        binding.tvErrorTextPasswordAndServer.visibility = View.VISIBLE
    }

    private fun saveAccessToken(accessToken: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("access_token", accessToken)
        editor.apply()
    }
}

