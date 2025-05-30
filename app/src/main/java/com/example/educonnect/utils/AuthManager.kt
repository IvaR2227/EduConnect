package com.example.educonnect.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class AuthResult(
    val isSuccess: Boolean,
    val user: FirebaseUser? = null,
    val errorMessage: String? = null
)

class AuthManager {
    
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    
    /**
     * Sign in with email and password
     */
    suspend fun signInWithEmail(email: String, password: String): AuthResult {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            AuthResult(
                isSuccess = true,
                user = result.user
            )
        } catch (e: Exception) {
            AuthResult(
                isSuccess = false,
                errorMessage = e.message
            )
        }
    }
    
    /**
     * Create user with email and password
     */
    suspend fun createUserWithEmail(email: String, password: String): AuthResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            AuthResult(
                isSuccess = true,
                user = result.user
            )
        } catch (e: Exception) {
            AuthResult(
                isSuccess = false,
                errorMessage = e.message
            )
        }
    }
    
    /**
     * Send password reset email
     */
    suspend fun sendPasswordResetEmail(email: String): AuthResult {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthResult(isSuccess = true)
        } catch (e: Exception) {
            AuthResult(
                isSuccess = false,
                errorMessage = e.message
            )
        }
    }
    
    /**
     * Sign out current user
     */
    fun signOut() {
        auth.signOut()
    }
    
    /**
     * Get current user
     */
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
    
    /**
     * Check if user is signed in
     */
    fun isUserSignedIn(): Boolean {
        return auth.currentUser != null
    }
    
    /**
     * Save user profile data to Firestore
     */
    suspend fun saveUserProfile(
        userId: String,
        userData: Map<String, Any>
    ): AuthResult {
        return try {
            firestore.collection("users")
                .document(userId)
                .set(userData)
                .await()
            AuthResult(isSuccess = true)
        } catch (e: Exception) {
            AuthResult(
                isSuccess = false,
                errorMessage = e.message
            )
        }
    }
    
    /**
     * Get user profile data from Firestore
     */
    suspend fun getUserProfile(userId: String): Map<String, Any>? {
        return try {
            val document = firestore.collection("users")
                .document(userId)
                .get()
                .await()
            document.data
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Check if user is a teacher
     */
    suspend fun isTeacher(userId: String): Boolean {
        return try {
            val document = firestore.collection("users")
                .document(userId)
                .get()
                .await()
            val userType = document.getString("userType")
            userType == "teacher"
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Check if user is a student
     */
    suspend fun isStudent(userId: String): Boolean {
        return try {
            val document = firestore.collection("users")
                .document(userId)
                .get()
                .await()
            val userType = document.getString("userType")
            userType == "student"
        } catch (e: Exception) {
            false
        }
    }
    
    /**
     * Create teacher account
     */
    suspend fun createTeacherAccount(
        uniqueId: String,
        password: String,
        teacherData: Map<String, Any>
    ): AuthResult {
        val teacherEmail = "${uniqueId}@educonnect.teacher"
        
        val authResult = createUserWithEmail(teacherEmail, password)
        if (authResult.isSuccess && authResult.user != null) {
            // Save teacher profile with userType = "teacher"
            val userData = teacherData.toMutableMap()
            userData["userType"] = "teacher"
            userData["uniqueId"] = uniqueId
            userData["email"] = teacherEmail
            
            val profileResult = saveUserProfile(authResult.user.uid, userData)
            if (!profileResult.isSuccess) {
                // If profile creation fails, delete the auth user
                authResult.user.delete()
                return AuthResult(
                    isSuccess = false,
                    errorMessage = "Failed to create teacher profile: ${profileResult.errorMessage}"
                )
            }
        }
        
        return authResult
    }
    
    /**
     * Create student account
     */
    suspend fun createStudentAccount(
        email: String,
        password: String,
        studentData: Map<String, Any>
    ): AuthResult {
        val authResult = createUserWithEmail(email, password)
        if (authResult.isSuccess && authResult.user != null) {
            try {
                // Send email verification
                authResult.user.sendEmailVerification().await()
                
                // Save student profile with userType = "student"
                val userData = studentData.toMutableMap()
                userData["userType"] = "student"
                userData["email"] = email
                userData["emailVerified"] = false
                userData["createdAt"] = System.currentTimeMillis()
                
                val profileResult = saveUserProfile(authResult.user.uid, userData)
                if (!profileResult.isSuccess) {
                    // If profile creation fails, delete the auth user
                    authResult.user.delete()
                    return AuthResult(
                        isSuccess = false,
                        errorMessage = "Failed to create student profile: ${profileResult.errorMessage}"
                    )
                }
                
                return AuthResult(
                    isSuccess = true,
                    user = authResult.user
                )
            } catch (e: Exception) {
                // If email verification fails, still continue but log the error
                // The account creation was successful, verification can be retried
                return AuthResult(
                    isSuccess = true,
                    user = authResult.user,
                    errorMessage = "Account created but email verification failed: ${e.message}"
                )
            }
        }
        
        return authResult
    }
    
    /**
     * Check if current user's email is verified
     */
    fun isEmailVerified(): Boolean {
        return auth.currentUser?.isEmailVerified ?: false
    }
    
    /**
     * Send email verification to current user
     */
    suspend fun sendEmailVerification(): AuthResult {
        return try {
            val user = auth.currentUser
            if (user != null) {
                user.sendEmailVerification().await()
                AuthResult(isSuccess = true)
            } else {
                AuthResult(isSuccess = false, errorMessage = "No user logged in")
            }
        } catch (e: Exception) {
            AuthResult(isSuccess = false, errorMessage = e.message)
        }
    }

    /**
     * Reload current user to get updated email verification status
     */
    suspend fun reloadUser(): AuthResult {
        return try {
            val user = auth.currentUser
            if (user != null) {
                user.reload().await()
                AuthResult(isSuccess = true, user = user)
            } else {
                AuthResult(isSuccess = false, errorMessage = "No user logged in")
            }
        } catch (e: Exception) {
            AuthResult(isSuccess = false, errorMessage = e.message)
        }
    }

    /**
     * Update user email verification status in Firestore
     */
    suspend fun updateEmailVerificationStatus(userId: String, isVerified: Boolean): AuthResult {
        return try {
            firestore.collection("users")
                .document(userId)
                .update("emailVerified", isVerified)
                .await()
            AuthResult(isSuccess = true)
        } catch (e: Exception) {
            AuthResult(isSuccess = false, errorMessage = e.message)
        }
    }
} 