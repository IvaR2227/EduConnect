package com.example.educonnect

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

import androidx.lifecycle.lifecycleScope
import com.example.educonnect.databinding.ActivityMainBinding
import com.example.educonnect.ui.auth.StudentLoginActivity
import com.example.educonnect.ui.auth.TeacherLoginActivity
import com.example.educonnect.utils.ThemePreferences
import com.example.educonnect.utils.TestDataSetup
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var themePreferences: ThemePreferences
    private lateinit var testDataSetup: TestDataSetup
    private var isDarkMode = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize theme preferences and test data setup
        themePreferences = ThemePreferences(this)
        testDataSetup = TestDataSetup()
        
        // Apply saved theme
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
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupClickListeners()
        updateThemeIcon()
    }
    
    private fun setupClickListeners() {
        // Student login button (main)
        binding.btnMainStudentLogin.setOnClickListener {
            startActivity(Intent(this, StudentLoginActivity::class.java))
        }
        
        // Teacher login button (main)
        binding.btnMainTeacherLogin.setOnClickListener {
            startActivity(Intent(this, TeacherLoginActivity::class.java))
        }
        
        // Theme toggle button
        binding.btnThemeToggle.setOnClickListener {
            toggleTheme()
        }
        
        // Long press on app logo to create test accounts (for development)
        binding.imgAppIcon.setOnLongClickListener {
            createTestAccounts()
            true
        }
    }
    
    private fun toggleTheme() {
        lifecycleScope.launch {
            themePreferences.setDarkMode(!isDarkMode)
        }
    }
    
    private fun updateThemeIcon() {
        val iconRes = if (isDarkMode) {
            R.drawable.ic_light_mode
        } else {
            R.drawable.ic_dark_mode
        }
        binding.btnThemeToggle.setIconResource(iconRes)
    }
    
    private fun createTestAccounts() {
        testDataSetup.createAllTestAccounts { result ->
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Test Accounts Created")
                .setMessage(result)
                .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }
} 