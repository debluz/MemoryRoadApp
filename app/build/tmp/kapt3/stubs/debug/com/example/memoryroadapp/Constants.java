package com.example.memoryroadapp;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0004"}, d2 = {"Lcom/example/memoryroadapp/Constants;", "", "()V", "Companion", "app_debug"})
public class Constants {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AUTH_TAG = "FirebaseAuthAppTag";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TEST_TAG = "TestMemoryRoadAppTag";
    public static final int REQUEST_CODE_SING_IN = 1;
    public static final int REQUEST_CODE_SIGN_UP = 2;
    public static final int REQ_C_ADD_LOCATION = 3;
    public static final int REQ_C_EDIT_LOCATION = 4;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER = "user";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USERS = "users";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOCATIONS = "locations";
    public static final int EC_EMPTY_FIELDS = 5;
    public static final int EC_SIGN_IN_WITH_EMAIL = 6;
    public static final int EC_SIGN_IN_FAIL = 17;
    public static final int EC_SIGN_IN_GOOGLE = 7;
    public static final int EC_REGISTRATION_COMPLETED = 8;
    public static final int EC_REGISTRATION_FAILURE = 9;
    public static final int EC_CREATE_LOCATION = 10;
    public static final int EC_ADDED_LOCATION = 11;
    public static final int EC_UPDATED_LOCATION = 12;
    public static final int EC_FAIL_ADD_EDIT_LOCATION = 13;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_ID = "com.example.memoryroadapp.EXTRA_ID";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_NAME = "com.example.memoryroadapp.EXTRA_NAME";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SELECTED_LOCATIONS = "com.example.memoryroadapp.SELECTED_LOCATIONS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ALL_LOCATIONS = "com.example.memoryroadapp.ALL_LOCATIONS";
    public static final int REQUEST_EXTERNAL_STORAGE_AND_CAMERA = 14;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 15;
    public static final int REQUEST_IMAGE_GET = 16;
    public static final com.example.memoryroadapp.Constants.Companion Companion = null;
    
    public Constants() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0018\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/memoryroadapp/Constants$Companion;", "", "()V", "ALL_LOCATIONS", "", "AUTH_TAG", "EC_ADDED_LOCATION", "", "EC_CREATE_LOCATION", "EC_EMPTY_FIELDS", "EC_FAIL_ADD_EDIT_LOCATION", "EC_REGISTRATION_COMPLETED", "EC_REGISTRATION_FAILURE", "EC_SIGN_IN_FAIL", "EC_SIGN_IN_GOOGLE", "EC_SIGN_IN_WITH_EMAIL", "EC_UPDATED_LOCATION", "EXTRA_ID", "EXTRA_NAME", "LOCATIONS", "REQUEST_CODE_SIGN_UP", "REQUEST_CODE_SING_IN", "REQUEST_EXTERNAL_STORAGE_AND_CAMERA", "REQUEST_IMAGE_GET", "REQUEST_WRITE_EXTERNAL_STORAGE", "REQ_C_ADD_LOCATION", "REQ_C_EDIT_LOCATION", "SELECTED_LOCATIONS", "TEST_TAG", "USER", "USERS", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}