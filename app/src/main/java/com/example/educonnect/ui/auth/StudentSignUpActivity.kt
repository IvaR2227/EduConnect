package com.example.educonnect.ui.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.educonnect.R
import com.example.educonnect.databinding.ActivityStudentSignupBinding
import com.example.educonnect.ui.auth.fragments.SignUpStep1Fragment
import com.example.educonnect.ui.auth.fragments.SignUpStep2Fragment
import com.example.educonnect.utils.AuthManager
import com.example.educonnect.utils.EmailUtils
import kotlinx.coroutines.launch

class StudentSignUpActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStudentSignupBinding
    private lateinit var authManager: AuthManager
    private lateinit var pagerAdapter: SignUpPagerAdapter
    private var currentStep = 0
    
    // Student data to be collected across steps
    var studentData = mutableMapOf<String, String>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        authManager = AuthManager()
        
        setupToolbar()
        setupViewPager()
        setupClickListeners()
        updateStepIndicator()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            if (currentStep > 0) {
                // Go back to previous step
                hideKeyboard()
                binding.viewPager.currentItem = currentStep - 1
            } else {
                // Go back to login screen
                finish()
            }
        }
    }
    
    private fun setupViewPager() {
        pagerAdapter = SignUpPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = false // Disable swipe
        
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentStep = position
                updateStepIndicator()
                updateNextButton()
            }
        })
    }
    
    private fun setupClickListeners() {
        binding.btnNext.setOnClickListener {
            if (currentStep == 0) {
                // Validate step 1 and move to step 2
                if (validateStep1()) {
                    hideKeyboard()
                    binding.viewPager.currentItem = 1
                }
            } else if (currentStep == 1) {
                // Validate step 2 and create account
                if (validateStep2()) {
                    hideKeyboard()
                    createAccount()
                }
            }
        }
    }
    
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = currentFocus
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.windowToken, 0)
        }
    }
    
    private fun updateStepIndicator() {
        when (currentStep) {
            0 -> {
                binding.step1Circle.setBackgroundResource(R.drawable.step_circle_active)
                binding.step2Circle.setBackgroundResource(R.drawable.step_circle_inactive)
                binding.line1.setBackgroundColor(getColor(R.color.divider_color))
            }
            1 -> {
                binding.step1Circle.setBackgroundResource(R.drawable.step_circle_active)
                binding.step2Circle.setBackgroundResource(R.drawable.step_circle_active)
                binding.line1.setBackgroundColor(getColor(R.color.primary_blue))
            }
        }
    }
    
    private fun updateNextButton() {
        when (currentStep) {
            0 -> {
                binding.btnNext.text = getString(R.string.next)
                binding.btnNext.setIconResource(R.drawable.ic_arrow_forward)
            }
            1 -> {
                binding.btnNext.text = getString(R.string.create_account)
                binding.btnNext.setIconResource(R.drawable.ic_person)
            }
        }
    }
    
    private fun validateStep1(): Boolean {
        val step1Fragment = pagerAdapter.getFragment(0) as? SignUpStep1Fragment
        return step1Fragment?.validateAndSaveData() ?: false
    }
    
    private fun validateStep2(): Boolean {
        val step2Fragment = pagerAdapter.getFragment(1) as? SignUpStep2Fragment
        return step2Fragment?.validateAndSaveData() ?: false
    }
    
    private fun createAccount() {
        showLoading(true)
        
        lifecycleScope.launch {
            try {
                val email = studentData["email"] ?: ""
                val password = studentData["password"] ?: ""
                
                // Create student account using AuthManager
                val authResult = authManager.createStudentAccount(email, password, studentData.toMap())
                
                showLoading(false)
                
                if (authResult.isSuccess && authResult.user != null) {
                    // Send welcome email
                    sendWelcomeEmail(email, studentData["firstName"] ?: "Student")
                    
                    Toast.makeText(this@StudentSignUpActivity, 
                        "Account created successfully! Please check your email for verification.", 
                        Toast.LENGTH_LONG).show()
                    
                    // Navigate back to login
                    navigateToLogin()
                } else {
                    Toast.makeText(this@StudentSignUpActivity, 
                        authResult.errorMessage ?: "Failed to create account", 
                        Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                showLoading(false)
                Toast.makeText(this@StudentSignUpActivity, 
                    "Error: ${e.message}", 
                    Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun sendWelcomeEmail(email: String, firstName: String) {
        lifecycleScope.launch {
            try {
                EmailUtils.sendWelcomeEmail(email, firstName)
            } catch (e: Exception) {
                // Log the error but don't show to user
                e.printStackTrace()
            }
        }
    }
    
    private fun showLoading(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.btnNext.isEnabled = !show
        binding.viewPager.isUserInputEnabled = !show
    }
    
    private fun navigateToLogin() {
        val intent = Intent(this, StudentLoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        finish()
    }
    
    inner class SignUpPagerAdapter(activity: androidx.fragment.app.FragmentActivity) : 
        FragmentStateAdapter(activity) {
        
        private val fragments = mutableMapOf<Int, Fragment>()
        
        override fun getItemCount(): Int = 2
        
        override fun createFragment(position: Int): Fragment {
            val fragment = when (position) {
                0 -> SignUpStep1Fragment()
                1 -> SignUpStep2Fragment()
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
            fragments[position] = fragment
            return fragment
        }
        
        fun getFragment(position: Int): Fragment? = fragments[position]
    }
} 