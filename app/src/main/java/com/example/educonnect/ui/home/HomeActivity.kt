package com.example.educonnect.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.educonnect.MainActivity
import com.example.educonnect.R
import com.example.educonnect.databinding.ActivityHomeBinding
import com.example.educonnect.utils.AuthManager
import com.example.educonnect.utils.ThemePreferences
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    private lateinit var authManager: AuthManager
    private lateinit var themePreferences: ThemePreferences
    private var userType: String? = null
    private var isDarkMode = false
    
    companion object {
        const val EXTRA_USER_TYPE = "extra_user_type"
        const val EXTRA_USER_EMAIL = "extra_user_email"
        const val EXTRA_UNIQUE_ID = "extra_unique_id"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Initialize
        authManager = AuthManager()
        themePreferences = ThemePreferences(this)
        
        // Set up toolbar
        setSupportActionBar(binding.toolbar)
        
        // Get user data from intent
        userType = intent.getStringExtra(EXTRA_USER_TYPE)
        val userEmail = intent.getStringExtra(EXTRA_USER_EMAIL)
        val uniqueId = intent.getStringExtra(EXTRA_UNIQUE_ID)
        
        // Apply theme
        lifecycleScope.launch {
            themePreferences.isDarkMode().collect { darkMode ->
                isDarkMode = darkMode
                AppCompatDelegate.setDefaultNightMode(
                    if (darkMode) AppCompatDelegate.MODE_NIGHT_YES 
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
                updateThemeIcon()
            }
        }
        
        // Setup UI based on user type
        setupWelcomeMessage(userType, userEmail, uniqueId)
        setupClickListeners()
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_theme_toggle -> {
                toggleTheme()
                true
            }
            R.id.action_logout -> {
                handleLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun setupWelcomeMessage(userType: String?, userEmail: String?, uniqueId: String?) {
        when (userType) {
            "student" -> {
                binding.tvWelcomeMessage.text = getString(R.string.welcome_message_student)
                binding.tvUserInfo.text = "Email: $userEmail"
                binding.ivSuccessIcon.setImageResource(R.drawable.ic_student)
                binding.btnDashboard.text = "Student Dashboard"
            }
            "teacher" -> {
                binding.tvWelcomeMessage.text = getString(R.string.welcome_message_teacher)
                binding.tvUserInfo.text = "Teacher ID: $uniqueId"
                binding.ivSuccessIcon.setImageResource(R.drawable.ic_teacher)
                binding.btnDashboard.text = "Teacher Dashboard"
            }
            else -> {
                binding.tvWelcomeMessage.text = "Welcome to EduConnect!"
                binding.tvUserInfo.text = "Welcome to your learning portal"
                binding.ivSuccessIcon.setImageResource(R.drawable.ic_graduation_cap)
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnDashboard.setOnClickListener {
            handleDashboardClick()
        }
        
        binding.btnProfile.setOnClickListener {
            handleProfileClick()
        }
        
        binding.btnLogout.setOnClickListener {
            handleLogout()
        }
    }
    
    private fun handleDashboardClick() {
        val message = when (userType) {
            "student" -> "Student Dashboard coming soon!"
            "teacher" -> "Teacher Dashboard coming soon!"
            else -> "Dashboard coming soon!"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    private fun handleProfileClick() {
        Toast.makeText(this, "Profile page coming soon!", Toast.LENGTH_SHORT).show()
    }
    
    private fun handleLogout() {
        lifecycleScope.launch {
            try {
                authManager.signOut()
                Toast.makeText(this@HomeActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
                
                // Navigate back to main activity
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@HomeActivity, "Logout failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun toggleTheme() {
        lifecycleScope.launch {
            themePreferences.setDarkMode(!isDarkMode)
        }
    }
    
    private fun updateThemeIcon() {
        val menu = binding.toolbar.menu
        val themeItem = menu?.findItem(R.id.action_theme_toggle)
        val iconRes = if (isDarkMode) {
            R.drawable.ic_light_mode
        } else {
            R.drawable.ic_dark_mode
        }
        themeItem?.setIcon(iconRes)
    }
    
    @Deprecated("Deprecated in Java")
    @Suppress("MissingSuperCall")
    override fun onBackPressed() {
        // Show a toast instead of going back to login
        Toast.makeText(this, "Use logout to exit", Toast.LENGTH_SHORT).show()
        // Intentionally not calling super.onBackPressed() to prevent going back to login
    }
} 