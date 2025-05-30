package com.example.educonnect.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.educonnect.databinding.ActivityStudentLoginBinding
import com.example.educonnect.ui.home.HomeActivity
import com.example.educonnect.utils.AuthManager
import com.example.educonnect.utils.ValidationUtils
import kotlinx.coroutines.launch

class StudentLoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStudentLoginBinding
    private lateinit var authManager: AuthManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        authManager = AuthManager()
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }
        
        binding.tvForgotPassword.setOnClickListener {
            handleForgotPassword()
        }
        
        binding.tvSignUp.setOnClickListener {
            handleSignUp()
        }
    }
    
    private fun handleLogin() {
        val emailOrMobile = binding.etEmailMobile.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        
        // Validate inputs
        if (!validateInputs(emailOrMobile, password)) {
            return
        }
        
        // Show loading
        showLoading(true)
        
        // Check if input is email or mobile
        val isEmail = Patterns.EMAIL_ADDRESS.matcher(emailOrMobile).matches()
        
        if (isEmail) {
            // Login with email
            loginWithEmail(emailOrMobile, password)
        } else {
            // For mobile number, you might want to implement phone authentication
            // For now, we'll show a message
            showLoading(false)
            Toast.makeText(this, "Mobile login not implemented yet. Please use email.", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun loginWithEmail(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val result = authManager.signInWithEmail(email, password)
                showLoading(false)
                
                if (result.isSuccess) {
                    // Check email verification status
                    val user = authManager.getCurrentUser()
                    if (user != null) {
                        // Reload user to get fresh verification status
                        authManager.reloadUser()
                        
                        if (authManager.isEmailVerified()) {
                            Toast.makeText(this@StudentLoginActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                            // Navigate to home activity
                            navigateToHome(email)
                        } else {
                            // Email not verified
                            showEmailVerificationDialog(email)
                        }
                    } else {
                        Toast.makeText(this@StudentLoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@StudentLoginActivity, result.errorMessage ?: "Login failed", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                showLoading(false)
                Toast.makeText(this@StudentLoginActivity, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun validateInputs(emailOrMobile: String, password: String): Boolean {
        var isValid = true
        
        // Validate email or mobile
        if (emailOrMobile.isEmpty()) {
            binding.tilEmailMobile.error = "Email or mobile number is required"
            isValid = false
        } else if (!ValidationUtils.isValidEmailOrMobile(emailOrMobile)) {
            binding.tilEmailMobile.error = "Please enter a valid email or mobile number"
            isValid = false
        } else {
            binding.tilEmailMobile.error = null
        }
        
        // Validate password
        if (password.isEmpty()) {
            binding.tilPassword.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            binding.tilPassword.error = "Password must be at least 6 characters"
            isValid = false
        } else {
            binding.tilPassword.error = null
        }
        
        return isValid
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !show
    }
    
    private fun handleForgotPassword() {
        val emailOrMobile = binding.etEmailMobile.text.toString().trim()
        
        if (emailOrMobile.isEmpty()) {
            Toast.makeText(this, "Please enter your email first", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (!Patterns.EMAIL_ADDRESS.matcher(emailOrMobile).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Show loading
        showLoading(true)
        
        lifecycleScope.launch {
            try {
                val result = authManager.sendPasswordResetEmail(emailOrMobile)
                showLoading(false)
                
                if (result.isSuccess) {
                    Toast.makeText(this@StudentLoginActivity, "Password reset email sent!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@StudentLoginActivity, result.errorMessage ?: "Failed to send reset email", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                showLoading(false)
                Toast.makeText(this@StudentLoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun handleSignUp() {
        try {
            val intent = Intent(this, StudentSignUpActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error opening sign up: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
    
    private fun navigateToHome(email: String) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(HomeActivity.EXTRA_USER_TYPE, "student")
            putExtra(HomeActivity.EXTRA_USER_EMAIL, email)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    private fun showEmailVerificationDialog(email: String) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Email Verification Required")
            .setMessage("Please verify your email address before logging in. Check your inbox for a verification link.\n\nEmail: $email")
            .setPositiveButton("Resend Verification") { _, _ ->
                resendEmailVerification()
            }
            .setNegativeButton("OK") { dialog, _ ->
                dialog.dismiss()
                authManager.signOut() // Sign out unverified user
            }
            .setCancelable(false)
            .show()
    }

    private fun resendEmailVerification() {
        showLoading(true)
        lifecycleScope.launch {
            try {
                val result = authManager.sendEmailVerification()
                showLoading(false)
                
                if (result.isSuccess) {
                    Toast.makeText(this@StudentLoginActivity, "Verification email sent! Please check your inbox.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@StudentLoginActivity, "Failed to send verification email: ${result.errorMessage}", Toast.LENGTH_LONG).show()
                }
                authManager.signOut() // Sign out after sending verification
            } catch (e: Exception) {
                showLoading(false)
                Toast.makeText(this@StudentLoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                authManager.signOut()
            }
        }
    }
} 