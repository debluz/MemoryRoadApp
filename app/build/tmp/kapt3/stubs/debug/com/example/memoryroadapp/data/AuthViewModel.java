package com.example.memoryroadapp.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\nJ\u0006\u0010&\u001a\u00020#J\u0018\u0010\'\u001a\u00020#2\u0006\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u0010H\u0002J\u000e\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020,R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u000eR\"\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\tX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000eR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR \u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\f\"\u0004\b\u001f\u0010\u000eR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00100\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001c\u00a8\u0006-"}, d2 = {"Lcom/example/memoryroadapp/data/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "_eventCode", "Landroidx/lifecycle/MutableLiveData;", "", "authRepository", "Lcom/example/memoryroadapp/data/repositories/AuthRepository;", "authenticatedUserLiveData", "Landroidx/lifecycle/LiveData;", "Lcom/example/memoryroadapp/User;", "getAuthenticatedUserLiveData", "()Landroidx/lifecycle/LiveData;", "setAuthenticatedUserLiveData", "(Landroidx/lifecycle/LiveData;)V", "clickableContent", "", "getClickableContent", "()Ljava/lang/String;", "createdUserLiveData", "getCreatedUserLiveData", "setCreatedUserLiveData", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "getCurrentUser", "setCurrentUser", "emailEditTextContent", "getEmailEditTextContent", "()Landroidx/lifecycle/MutableLiveData;", "eventCode", "getEventCode", "setEventCode", "passwordEditTextContent", "getPasswordEditTextContent", "checkIfAnyoneIsAuthenticated", "", "createUser", "authenticatedUser", "onSignInButtonClick", "signInWithEmail", "email", "password", "signInWithGoogle", "googleAuthCredential", "Lcom/google/firebase/auth/AuthCredential;", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.memoryroadapp.data.repositories.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<com.google.firebase.auth.FirebaseUser> currentUser;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<com.example.memoryroadapp.User> authenticatedUserLiveData;
    @org.jetbrains.annotations.NotNull()
    public androidx.lifecycle.LiveData<com.example.memoryroadapp.User> createdUserLiveData;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> emailEditTextContent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> passwordEditTextContent = null;
    private androidx.lifecycle.MutableLiveData<java.lang.Integer> _eventCode;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.LiveData<java.lang.Integer> eventCode;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String clickableContent = "Register here";
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.google.firebase.auth.FirebaseUser> getCurrentUser() {
        return null;
    }
    
    public final void setCurrentUser(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.google.firebase.auth.FirebaseUser> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.memoryroadapp.User> getAuthenticatedUserLiveData() {
        return null;
    }
    
    public final void setAuthenticatedUserLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.example.memoryroadapp.User> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.example.memoryroadapp.User> getCreatedUserLiveData() {
        return null;
    }
    
    public final void setCreatedUserLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<com.example.memoryroadapp.User> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getEmailEditTextContent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getPasswordEditTextContent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getEventCode() {
        return null;
    }
    
    public final void setEventCode(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.LiveData<java.lang.Integer> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getClickableContent() {
        return null;
    }
    
    public final void onSignInButtonClick() {
    }
    
    private final void signInWithEmail(java.lang.String email, java.lang.String password) {
    }
    
    public final void signInWithGoogle(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential googleAuthCredential) {
    }
    
    public final void createUser(@org.jetbrains.annotations.NotNull()
    com.example.memoryroadapp.User authenticatedUser) {
    }
    
    public final void checkIfAnyoneIsAuthenticated() {
    }
    
    public AuthViewModel() {
        super();
    }
}