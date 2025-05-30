package com.example.educonnect.ui.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\u0010\u0010\u001f\u001a\u00020\u00132\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0013H\u0002J\b\u0010#\u001a\u00020\u0013H\u0002J\b\u0010$\u001a\u00020!H\u0002J\b\u0010%\u001a\u00020!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\'"}, d2 = {"Lcom/example/educonnect/ui/auth/StudentSignUpActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/example/educonnect/utils/AuthManager;", "binding", "Lcom/example/educonnect/databinding/ActivityStudentSignupBinding;", "currentStep", "", "pagerAdapter", "Lcom/example/educonnect/ui/auth/StudentSignUpActivity$SignUpPagerAdapter;", "studentData", "", "", "getStudentData", "()Ljava/util/Map;", "setStudentData", "(Ljava/util/Map;)V", "createAccount", "", "hideKeyboard", "navigateToLogin", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "sendWelcomeEmail", "email", "firstName", "setupClickListeners", "setupToolbar", "setupViewPager", "showLoading", "show", "", "updateNextButton", "updateStepIndicator", "validateStep1", "validateStep2", "SignUpPagerAdapter", "app_debug"})
public final class StudentSignUpActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.educonnect.databinding.ActivityStudentSignupBinding binding;
    private com.example.educonnect.utils.AuthManager authManager;
    private com.example.educonnect.ui.auth.StudentSignUpActivity.SignUpPagerAdapter pagerAdapter;
    private int currentStep = 0;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.String, java.lang.String> studentData;
    
    public StudentSignUpActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.String> getStudentData() {
        return null;
    }
    
    public final void setStudentData(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupViewPager() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void hideKeyboard() {
    }
    
    private final void updateStepIndicator() {
    }
    
    private final void updateNextButton() {
    }
    
    private final boolean validateStep1() {
        return false;
    }
    
    private final boolean validateStep2() {
        return false;
    }
    
    private final void createAccount() {
    }
    
    private final void sendWelcomeEmail(java.lang.String email, java.lang.String firstName) {
    }
    
    private final void showLoading(boolean show) {
    }
    
    private final void navigateToLogin() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u0007J\b\u0010\f\u001a\u00020\u0007H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/educonnect/ui/auth/StudentSignUpActivity$SignUpPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Lcom/example/educonnect/ui/auth/StudentSignUpActivity;Landroidx/fragment/app/FragmentActivity;)V", "fragments", "", "", "Landroidx/fragment/app/Fragment;", "createFragment", "position", "getFragment", "getItemCount", "app_debug"})
    public final class SignUpPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        @org.jetbrains.annotations.NotNull()
        private final java.util.Map<java.lang.Integer, androidx.fragment.app.Fragment> fragments = null;
        
        public SignUpPagerAdapter(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.FragmentActivity activity) {
            super(null);
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public androidx.fragment.app.Fragment createFragment(int position) {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final androidx.fragment.app.Fragment getFragment(int position) {
            return null;
        }
    }
}