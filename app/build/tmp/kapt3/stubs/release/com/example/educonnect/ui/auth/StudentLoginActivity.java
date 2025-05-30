package com.example.educonnect.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\bH\u0002J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\bH\u0002J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/example/educonnect/ui/auth/StudentLoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/example/educonnect/utils/AuthManager;", "binding", "Lcom/example/educonnect/databinding/ActivityStudentLoginBinding;", "handleForgotPassword", "", "handleLogin", "handleSignUp", "loginWithEmail", "email", "", "password", "navigateToHome", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "showLoading", "show", "", "validateInputs", "emailOrMobile", "app_release"})
public final class StudentLoginActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.educonnect.databinding.ActivityStudentLoginBinding binding;
    private com.example.educonnect.utils.AuthManager authManager;
    
    public StudentLoginActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void handleLogin() {
    }
    
    private final void loginWithEmail(java.lang.String email, java.lang.String password) {
    }
    
    private final boolean validateInputs(java.lang.String emailOrMobile, java.lang.String password) {
        return false;
    }
    
    private final void showLoading(boolean show) {
    }
    
    private final void handleForgotPassword() {
    }
    
    private final void handleSignUp() {
    }
    
    private final void navigateToHome(java.lang.String email) {
    }
}