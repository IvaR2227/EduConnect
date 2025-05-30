package com.example.educonnect.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.educonnect.MainActivity
import com.example.educonnect.databinding.ActivityTeacherLoginBinding
import com.example.educonnect.ui.home.HomeActivity
import com.example.educonnect.utils.AuthManager
import kotlinx.coroutines.launch

class TeacherLoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityTeacherLoginBinding
    private lateinit var authManager: AuthManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        authManager = AuthManager()
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            handleLogin()
        }
        
        binding.tvBackToHome.setOnClickListener {
            handleBackToHome()
        }
    }
    
    private fun handleLogin() {
        val uniqueId = binding.etUniqueId.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        
        // Validate inputs
        if (!validateInputs(uniqueId, password)) {
            return
        }
        
        // Show loading
        showLoading(true)
        
        // For teacher login, we'll use the unique ID as part of email
        // Format: teacherID@educonnect.teacher (or your custom domain)
        val teacherEmail = "${uniqueId}@educonnect.teacher"
        
        loginWithTeacherCredentials(teacherEmail, password)
    }
    
    private fun loginWithTeacherCredentials(email: String, password: String) {
        lifecycleScope.launch {
            try {
                val result = authManager.signInWithEmail(email, password)
                showLoading(false)
                
                if (result.isSuccess) {
                    // Verify if this is a teacher account
                    val user = authManager.getCurrentUser()
                    if (user != null) {
                        // Check teacher role in Firestore
                        verifyTeacherRole(user.uid)
                    } else {
                        Toast.makeText(this@TeacherLoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@TeacherLoginActivity, result.errorMessage ?: "Login failed", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                showLoading(false)
                Toast.makeText(this@TeacherLoginActivity, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun verifyTeacherRole(userId: String) {
        lifecycleScope.launch {
            try {
                val isTeacher = authManager.isTeacher(userId)
                if (isTeacher) {
                    Toast.makeText(this@TeacherLoginActivity, "Welcome, Teacher!", Toast.LENGTH_SHORT).show()
                    // Navigate to home activity
                    navigateToHome()
                } else {
                    Toast.makeText(this@TeacherLoginActivity, "Access denied. Teacher account required.", Toast.LENGTH_LONG).show()
                    authManager.signOut()
                }
            } catch (e: Exception) {
                Toast.makeText(this@TeacherLoginActivity, "Verification failed: ${e.message}", Toast.LENGTH_LONG).show()
                authManager.signOut()
            }
        }
    }
    
    private fun validateInputs(uniqueId: String, password: String): Boolean {
        var isValid = true
        
        // Validate unique ID
        if (uniqueId.isEmpty()) {
            binding.tilUniqueId.error = "Unique ID is required"
            isValid = false
        } else if (uniqueId.length < 3) {
            binding.tilUniqueId.error = "Unique ID must be at least 3 characters"
            isValid = false
        } else {
            binding.tilUniqueId.error = null
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
    
    private fun handleBackToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    
    private fun navigateToHome() {
        val uniqueId = binding.etUniqueId.text.toString().trim()
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(HomeActivity.EXTRA_USER_TYPE, "teacher")
            putExtra(HomeActivity.EXTRA_UNIQUE_ID, uniqueId)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
} 