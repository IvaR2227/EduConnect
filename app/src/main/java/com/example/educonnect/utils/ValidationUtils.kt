package com.example.educonnect.utils

import android.util.Patterns

object ValidationUtils {
    
    /**
     * Check if the input is a valid email or mobile number
     */
    fun isValidEmailOrMobile(input: String): Boolean {
        return isValidEmail(input) || isValidMobile(input)
    }
    
    /**
     * Check if the input is a valid email
     */
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    /**
     * Check if the input is a valid mobile number
     * This is a simple validation, you might want to use more sophisticated validation
     * depending on your requirements and supported countries
     */
    fun isValidMobile(mobile: String): Boolean {
        return mobile.length >= 10 && mobile.all { it.isDigit() }
    }
    
    /**
     * Check if the password is valid
     */
    fun isValidPassword(password: String): Boolean {
        // At least 6 characters
        return password.length >= 6
    }
} 