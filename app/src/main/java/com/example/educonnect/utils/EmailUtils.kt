package com.example.educonnect.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object EmailUtils {
    
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    
    /**
     * Send welcome email to new student
     * This would typically integrate with an email service like SendGrid, AWS SES, etc.
     * For now, we'll save the email notification to Firestore and show success
     */
    suspend fun sendWelcomeEmail(email: String, firstName: String) {
        try {
            // In a real app, you would use an email service like:
            // - SendGrid
            // - AWS SES
            // - Firebase Functions with Nodemailer
            
            // For demo purposes, we'll save the notification to Firestore
            val emailData = mapOf(
                "to" to email,
                "subject" to "Welcome to EduConnect! 🎓",
                "body" to createWelcomeEmailBody(firstName, email),
                "type" to "welcome",
                "timestamp" to System.currentTimeMillis(),
                "status" to "sent"
            )
            
            firestore.collection("email_notifications")
                .add(emailData)
                .await()
                
            // Send Firebase email verification
            auth.currentUser?.sendEmailVerification()?.await()
            
        } catch (e: Exception) {
            throw e
        }
    }
    
    /**
     * Send password reset email notification
     */
    suspend fun sendPasswordResetNotification(email: String) {
        try {
            val emailData = mapOf(
                "to" to email,
                "subject" to "Password Reset - EduConnect",
                "body" to createPasswordResetEmailBody(),
                "type" to "password_reset",
                "timestamp" to System.currentTimeMillis(),
                "status" to "sent"
            )
            
            firestore.collection("email_notifications")
                .add(emailData)
                .await()
                
        } catch (e: Exception) {
            throw e
        }
    }
    
    /**
     * Send account creation success notification
     */
    suspend fun sendAccountCreationSuccess(email: String, firstName: String, userType: String) {
        try {
            val emailData = mapOf(
                "to" to email,
                "subject" to "Account Created Successfully - EduConnect",
                "body" to createAccountSuccessEmailBody(firstName, userType),
                "type" to "account_success",
                "timestamp" to System.currentTimeMillis(),
                "status" to "sent"
            )
            
            firestore.collection("email_notifications")
                .add(emailData)
                .await()
                
        } catch (e: Exception) {
            throw e
        }
    }
    
    private fun createWelcomeEmailBody(firstName: String, email: String): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                    .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                    .button { background: #667eea; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; display: inline-block; margin: 20px 0; }
                    .footer { text-align: center; margin-top: 30px; color: #666; font-size: 14px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>🎓 Welcome to EduConnect!</h1>
                        <p>Your journey to better education starts here</p>
                    </div>
                    <div class="content">
                        <h2>Hi $firstName!</h2>
                        <p>Congratulations! Your EduConnect student account has been created successfully. We're excited to have you join our learning community.</p>
                        
                        <h3>What's Next?</h3>
                        <ul>
                            <li>✅ Verify your email address by clicking the verification link</li>
                            <li>📚 Complete your profile to get personalized recommendations</li>
                            <li>🔍 Explore courses and connect with teachers</li>
                            <li>📱 Download our mobile app for learning on the go</li>
                        </ul>
                        
                        <a href="#" class="button">Verify Email Address</a>
                        
                        <h3>Need Help?</h3>
                        <p>If you have any questions or need assistance, our support team is here to help. Contact us at support@educonnect.com</p>
                        
                        <p>Happy Learning!<br>The EduConnect Team</p>
                    </div>
                    <div class="footer">
                        <p>&copy; 2024 EduConnect. All rights reserved.</p>
                        <p>This email was sent to $email</p>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }
    
    private fun createPasswordResetEmailBody(): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background: #ff6b6b; color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                    .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                    .button { background: #ff6b6b; color: white; padding: 12px 24px; text-decoration: none; border-radius: 5px; display: inline-block; margin: 20px 0; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>🔐 Password Reset Request</h1>
                    </div>
                    <div class="content">
                        <p>We received a request to reset your EduConnect password.</p>
                        <p>If you requested this password reset, click the button below to set a new password:</p>
                        <a href="#" class="button">Reset Password</a>
                        <p>If you didn't request this password reset, please ignore this email. Your password will remain unchanged.</p>
                        <p>For security reasons, this link will expire in 24 hours.</p>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }
    
    private fun createAccountSuccessEmailBody(firstName: String, userType: String): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                    .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                    .header { background: #4CAF50; color: white; padding: 30px; text-align: center; border-radius: 10px 10px 0 0; }
                    .content { background: #f9f9f9; padding: 30px; border-radius: 0 0 10px 10px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>✅ Account Created Successfully!</h1>
                    </div>
                    <div class="content">
                        <h2>Hi $firstName!</h2>
                        <p>Your EduConnect $userType account has been created successfully and is now active.</p>
                        <p>You can now log in to your account and start using all the features available to you.</p>
                        <p>Thank you for choosing EduConnect!</p>
                    </div>
                </div>
            </body>
            </html>
        """.trimIndent()
    }
} 