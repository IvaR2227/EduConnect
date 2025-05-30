package com.example.educonnect.ui.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0017J\u0012\u0010\u0012\u001a\u00020\u000e2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u000eH\u0002J&\u0010\u001c\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\f2\b\u0010\u001e\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010\u001f\u001a\u00020\u000eH\u0002J\b\u0010 \u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/example/educonnect/ui/home/HomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authManager", "Lcom/example/educonnect/utils/AuthManager;", "binding", "Lcom/example/educonnect/databinding/ActivityHomeBinding;", "isDarkMode", "", "themePreferences", "Lcom/example/educonnect/utils/ThemePreferences;", "userType", "", "handleDashboardClick", "", "handleLogout", "handleProfileClick", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "setupClickListeners", "setupWelcomeMessage", "userEmail", "uniqueId", "toggleTheme", "updateThemeIcon", "Companion", "app_release"})
public final class HomeActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.educonnect.databinding.ActivityHomeBinding binding;
    private com.example.educonnect.utils.AuthManager authManager;
    private com.example.educonnect.utils.ThemePreferences themePreferences;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String userType;
    private boolean isDarkMode = false;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_USER_TYPE = "extra_user_type";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_USER_EMAIL = "extra_user_email";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_UNIQUE_ID = "extra_unique_id";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.educonnect.ui.home.HomeActivity.Companion Companion = null;
    
    public HomeActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    private final void setupWelcomeMessage(java.lang.String userType, java.lang.String userEmail, java.lang.String uniqueId) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void handleDashboardClick() {
    }
    
    private final void handleProfileClick() {
    }
    
    private final void handleLogout() {
    }
    
    private final void toggleTheme() {
    }
    
    private final void updateThemeIcon() {
    }
    
    @java.lang.Override()
    @kotlin.Suppress(names = {"MissingSuperCall"})
    @java.lang.Deprecated()
    public void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/educonnect/ui/home/HomeActivity$Companion;", "", "()V", "EXTRA_UNIQUE_ID", "", "EXTRA_USER_EMAIL", "EXTRA_USER_TYPE", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}