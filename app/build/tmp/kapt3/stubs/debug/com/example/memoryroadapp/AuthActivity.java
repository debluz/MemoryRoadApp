package com.example.memoryroadapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\"\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u000eH\u0014J\b\u0010\u001f\u001a\u00020\u000eH\u0002J\u0012\u0010 \u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/memoryroadapp/AuthActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "authViewModel", "Lcom/example/memoryroadapp/data/AuthViewModel;", "getAuthViewModel", "()Lcom/example/memoryroadapp/data/AuthViewModel;", "authViewModel$delegate", "Lkotlin/Lazy;", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "googleSignInClient", "Lcom/google/android/gms/auth/api/signin/GoogleSignInClient;", "checkIfUserIsAlreadyAuthenticated", "", "createNewUser", "authenticatedUser", "Lcom/example/memoryroadapp/User;", "goToMainActivity", "initButtons", "initGoogleSignInClient", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "signInWithGoogle", "signInWithGoogleAuthCredential", "idToken", "", "app_debug"})
public final class AuthActivity extends androidx.appcompat.app.AppCompatActivity {
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private com.google.android.gms.auth.api.signin.GoogleSignInClient googleSignInClient;
    private final kotlin.Lazy authViewModel$delegate = null;
    private java.util.HashMap _$_findViewCache;
    
    private final com.example.memoryroadapp.data.AuthViewModel getAuthViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initButtons() {
    }
    
    private final void initGoogleSignInClient() {
    }
    
    private final void signInWithGoogle() {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void signInWithGoogleAuthCredential(java.lang.String idToken) {
    }
    
    private final void createNewUser(com.example.memoryroadapp.User authenticatedUser) {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    private final void checkIfUserIsAlreadyAuthenticated() {
    }
    
    private final void goToMainActivity() {
    }
    
    public AuthActivity() {
        super();
    }
}