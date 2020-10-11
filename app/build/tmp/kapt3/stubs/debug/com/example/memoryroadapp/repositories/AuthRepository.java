package com.example.memoryroadapp.repositories;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bJ$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u0015J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\nJ\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/memoryroadapp/repositories/AuthRepository;", "", "()V", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "rootRef", "Lcom/google/firebase/firestore/FirebaseFirestore;", "usersRef", "Lcom/google/firebase/firestore/CollectionReference;", "createUserInFirestoreIfNotExists", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/memoryroadapp/User;", "authenticatedUser", "createUserWithEmail", "email", "", "password", "name", "firebaseSignInWithEmail", "firebaseSignInWithGoogle", "googleAuthCredential", "Lcom/google/firebase/auth/AuthCredential;", "getCurrentUser", "Lcom/google/firebase/auth/FirebaseUser;", "signOut", "", "app_debug"})
public final class AuthRepository {
    private final com.google.firebase.auth.FirebaseAuth firebaseAuth = null;
    private final com.google.firebase.firestore.FirebaseFirestore rootRef = null;
    private final com.google.firebase.firestore.CollectionReference usersRef = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.memoryroadapp.User> firebaseSignInWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.memoryroadapp.User> firebaseSignInWithGoogle(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.AuthCredential googleAuthCredential) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.memoryroadapp.User> createUserInFirestoreIfNotExists(@org.jetbrains.annotations.NotNull()
    com.example.memoryroadapp.User authenticatedUser) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.memoryroadapp.User> createUserWithEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.google.firebase.auth.FirebaseUser> getCurrentUser() {
        return null;
    }
    
    public final void signOut() {
    }
    
    public AuthRepository() {
        super();
    }
}