package com.example.educonnect.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Utility class for setting up test accounts during development
 * Use this to create initial teacher and student accounts for testing
 */
class TestDataSetup {
    
    private val authManager = AuthManager()
    
    /**
     * Create a test teacher account
     * Call this from MainActivity or any activity during development
     */
    fun createTestTeacher(
        uniqueId: String = "teacher001",
        password: String = "password123",
        name: String = "John Smith",
        subject: String = "Mathematics",
        onResult: (Boolean, String) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val teacherData = mapOf(
                    "name" to name,
                    "subject" to subject,
                    "phone" to "1234567890",
                    "department" to "Science"
                )
                
                val result = authManager.createTeacherAccount(
                    uniqueId = uniqueId,
                    password = password,
                    teacherData = teacherData
                )
                
                if (result.isSuccess) {
                    onResult(true, "Teacher account created successfully!\nUnique ID: $uniqueId\nPassword: $password")
                } else {
                    onResult(false, "Failed to create teacher account: ${result.errorMessage}")
                }
            } catch (e: Exception) {
                onResult(false, "Error creating teacher account: ${e.message}")
            }
        }
    }
    
    /**
     * Create a test student account
     */
    fun createTestStudent(
        email: String = "student@example.com",
        password: String = "password123",
        name: String = "Jane Doe",
        grade: String = "10",
        onResult: (Boolean, String) -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val studentData = mapOf(
                    "name" to name,
                    "grade" to grade,
                    "rollNumber" to "S001",
                    "phone" to "9876543210"
                )
                
                val result = authManager.createStudentAccount(
                    email = email,
                    password = password,
                    studentData = studentData
                )
                
                if (result.isSuccess) {
                    onResult(true, "Student account created successfully!\nEmail: $email\nPassword: $password")
                } else {
                    onResult(false, "Failed to create student account: ${result.errorMessage}")
                }
            } catch (e: Exception) {
                onResult(false, "Error creating student account: ${e.message}")
            }
        }
    }
    
    /**
     * Create multiple test accounts at once
     */
    fun createAllTestAccounts(onResult: (String) -> Unit) {
        val results = mutableListOf<String>()
        
        // Create teacher account
        createTestTeacher { success, message ->
            results.add("Teacher: $message")
            
            // Create student account after teacher is done
            createTestStudent { success2, message2 ->
                results.add("Student: $message2")
                onResult(results.joinToString("\n\n"))
            }
        }
    }
} 