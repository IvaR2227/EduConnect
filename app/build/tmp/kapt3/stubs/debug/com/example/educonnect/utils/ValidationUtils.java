package com.example.educonnect.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0006\u00a8\u0006\r"}, d2 = {"Lcom/example/educonnect/utils/ValidationUtils;", "", "()V", "isValidEmail", "", "email", "", "isValidEmailOrMobile", "input", "isValidMobile", "mobile", "isValidPassword", "password", "app_debug"})
public final class ValidationUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.educonnect.utils.ValidationUtils INSTANCE = null;
    
    private ValidationUtils() {
        super();
    }
    
    /**
     * Check if the input is a valid email or mobile number
     */
    public final boolean isValidEmailOrMobile(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return false;
    }
    
    /**
     * Check if the input is a valid email
     */
    public final boolean isValidEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return false;
    }
    
    /**
     * Check if the input is a valid mobile number
     * This is a simple validation, you might want to use more sophisticated validation
     * depending on your requirements and supported countries
     */
    public final boolean isValidMobile(@org.jetbrains.annotations.NotNull()
    java.lang.String mobile) {
        return false;
    }
    
    /**
     * Check if the password is valid
     */
    public final boolean isValidPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return false;
    }
}