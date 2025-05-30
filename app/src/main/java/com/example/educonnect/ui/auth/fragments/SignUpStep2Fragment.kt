package com.example.educonnect.ui.auth.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.educonnect.R
import com.example.educonnect.databinding.FragmentSignupStep2Binding
import com.example.educonnect.ui.auth.StudentSignUpActivity

class SignUpStep2Fragment : Fragment() {
    
    private var _binding: FragmentSignupStep2Binding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupStep2Binding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupPasswordValidation()
    }
    
    private fun setupPasswordValidation() {
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                updatePasswordRequirements(s.toString())
            }
        })
        
        binding.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            
            override fun afterTextChanged(s: Editable?) {
                validatePasswordMatch()
            }
        })
    }
    
    private fun updatePasswordRequirements(password: String) {
        val colorGreen = ContextCompat.getColor(requireContext(), R.color.success_green)
        val colorGray = ContextCompat.getColor(requireContext(), R.color.text_secondary)
        
        // Requirement 1: At least 8 characters
        if (password.length >= 8) {
            binding.tvRequirement1.setTextColor(colorGreen)
            binding.tvRequirement1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0)
            binding.tvRequirement1.compoundDrawables[0]?.setTint(colorGreen)
        } else {
            binding.tvRequirement1.setTextColor(colorGray)
            binding.tvRequirement1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0)
            binding.tvRequirement1.compoundDrawables[0]?.setTint(colorGray)
        }
        
        // Requirement 2: Contains uppercase letter
        if (password.any { it.isUpperCase() }) {
            binding.tvRequirement2.setTextColor(colorGreen)
            binding.tvRequirement2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0)
            binding.tvRequirement2.compoundDrawables[0]?.setTint(colorGreen)
        } else {
            binding.tvRequirement2.setTextColor(colorGray)
            binding.tvRequirement2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0)
            binding.tvRequirement2.compoundDrawables[0]?.setTint(colorGray)
        }
        
        // Requirement 3: Contains number or symbol
        if (password.any { it.isDigit() || !it.isLetterOrDigit() }) {
            binding.tvRequirement3.setTextColor(colorGreen)
            binding.tvRequirement3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0)
            binding.tvRequirement3.compoundDrawables[0]?.setTint(colorGreen)
        } else {
            binding.tvRequirement3.setTextColor(colorGray)
            binding.tvRequirement3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check_circle, 0, 0, 0)
            binding.tvRequirement3.compoundDrawables[0]?.setTint(colorGray)
        }
    }
    
    private fun validatePasswordMatch() {
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        
        if (confirmPassword.isNotEmpty() && password != confirmPassword) {
            binding.tilConfirmPassword.error = "Passwords do not match"
        } else {
            binding.tilConfirmPassword.error = null
        }
    }
    
    fun validateAndSaveData(): Boolean {
        var isValid = true
        
        // Validate email
        val email = binding.etEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.tilEmail.error = "Email address is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Please enter a valid email address"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }
        
        // Validate password
        val password = binding.etPassword.text.toString()
        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            isValid = false
        } else if (!isPasswordValid(password)) {
            binding.tilPassword.error = "Password does not meet requirements"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }
        
        // Validate confirm password
        val confirmPassword = binding.etConfirmPassword.text.toString()
        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Please confirm your password"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Passwords do not match"
            isValid = false
        } else {
            binding.tilConfirmPassword.error = null
        }
        
        // Validate terms and conditions
        if (!binding.cbTerms.isChecked) {
            // Show a toast or snackbar for terms
            isValid = false
        }
        
        // Save data if valid
        if (isValid) {
            val signUpActivity = activity as StudentSignUpActivity
            signUpActivity.studentData.apply {
                put("email", email)
                put("password", password)
                put("createdAt", System.currentTimeMillis().toString())
            }
        }
        
        return isValid
    }
    
    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isUpperCase() } &&
                password.any { it.isDigit() || !it.isLetterOrDigit() }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 