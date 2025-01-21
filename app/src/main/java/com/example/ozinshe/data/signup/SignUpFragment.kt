package com.example.ozinshe.data.signup

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ozinshe.R
import com.example.ozinshe.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signUpResponse.observe(viewLifecycleOwner) {
            binding.tvErrorEmailSignUp.visibility = View.GONE
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
            saveAccessToken(viewModel.signUpResponse.value!!.accessToken)
            findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }
        viewModel.errorResponse.observe(viewLifecycleOwner) {
            showError(it)
        }

        binding.btnBackSignUpFragment1.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnShowPasswordSignUp.setOnClickListener {
            togglePasswordVisibility(binding.editTextPasswordSignUp)
        }

        binding.btnShowPasswordCheck.setOnClickListener {
            togglePasswordVisibility(binding.editTextConfirmPassword)
        }

        binding.btnSignUp.setOnClickListener {
            val email = binding.editTextEmailSignUp.text.toString()
            val password = binding.editTextPasswordSignUp.text.toString()
            val confirmPassword = binding.editTextConfirmPassword.text.toString()

            val emailIsValid = emailValidation(email)
            val passwordIsValid = validationPassword(password, confirmPassword)

            if (emailIsValid && passwordIsValid) {
                viewModel.signUpUser(email, password)
            } else {
                validationEmail(emailIsValid)
            }
        }
        binding.tvSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }


    private fun togglePasswordVisibility(passwordEditText: EditText) {
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
            binding.tvErrorEmailSignUp.text = ""
            binding.tvErrorEmailSignUp.visibility = View.GONE
            binding.editTextEmailSignUp.setBackgroundResource(R.drawable.edittext_focus_action_12dp)
        } else {
            binding.tvErrorEmailSignUp.text = "Қате формат"
            binding.tvErrorEmailSignUp.visibility = View.VISIBLE
            binding.editTextEmailSignUp.setBackgroundResource(R.drawable.background_edittext_12dp_error)
        }
    }

    private fun validationPassword(password: String, confirmPassword: String): Boolean {
        return if (password.length < 6) {
            binding.tvErrorEmailSignUp.text = "Құпия сөз 6 таңбадан кем болмауы керек"
            binding.tvErrorEmailSignUp.visibility = View.VISIBLE
            binding.editTextPasswordSignUp.setBackgroundResource(R.drawable.background_edittext_12dp_error)
            false
        } else if (password != confirmPassword) {
            binding.tvErrorEmailSignUp.text = "Құпия сөздер сәйкес келмейді"
            binding.tvErrorEmailSignUp.visibility = View.VISIBLE
            binding.editTextConfirmPassword.setBackgroundResource(R.drawable.background_edittext_12dp_error)
            false
        } else {
            binding.tvErrorEmailSignUp.visibility = View.GONE
            binding.editTextPasswordSignUp.setBackgroundResource(R.drawable.edittext_focus_action_12dp)
            binding.editTextConfirmPassword.setBackgroundResource(R.drawable.edittext_focus_action_12dp)
            true
        }
    }


    private fun showError(message: String) {
        binding.tvErrorEmailSignUp.text = message
        binding.tvErrorEmailSignUp.visibility = View.VISIBLE
    }

    private fun saveAccessToken(accessToken: String) {
        val sharedPreferences =
            requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("access_token", accessToken)
        editor.apply()
    }
}
