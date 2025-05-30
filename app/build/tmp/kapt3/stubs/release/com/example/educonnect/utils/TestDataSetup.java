package com.example.educonnect.utils;

/**
 * Utility class for setting up test accounts during development
 * Use this to create initial teacher and student accounts for testing
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\bJH\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\u000fJH\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\t2\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/example/educonnect/utils/TestDataSetup;", "", "()V", "authManager", "Lcom/example/educonnect/utils/AuthManager;", "createAllTestAccounts", "", "onResult", "Lkotlin/Function1;", "", "createTestStudent", "email", "password", "name", "grade", "Lkotlin/Function2;", "", "createTestTeacher", "uniqueId", "subject", "app_release"})
public final class TestDataSetup {
    @org.jetbrains.annotations.NotNull()
    private final com.example.educonnect.utils.AuthManager authManager = null;
    
    public TestDataSetup() {
        super();
    }
    
    /**
     * Create a test teacher account
     * Call this from MainActivity or any activity during development
     */
    public final void createTestTeacher(@org.jetbrains.annotations.NotNull()
    java.lang.String uniqueId, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String subject, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
    }
    
    /**
     * Create a test student account
     */
    public final void createTestStudent(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String grade, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> onResult) {
    }
    
    /**
     * Create multiple test accounts at once
     */
    public final void createAllTestAccounts(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onResult) {
    }
}