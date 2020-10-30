package com.example.memoryroadapp.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 0}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007\u001a \u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0007\u001a\u001a\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0007\u001a\u001e\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007\u00a8\u0006\u0019"}, d2 = {"addTextWatcher", "", "view", "Lcom/google/android/material/textfield/TextInputEditText;", "textWatcher", "Landroid/text/TextWatcher;", "checkIfFormValid", "Landroid/widget/Button;", "validation", "", "checkIfValid", "Lcom/google/android/material/textfield/TextInputLayout;", "errorMessage", "", "loadImage", "Landroid/widget/ImageView;", "bitmap", "Landroid/graphics/Bitmap;", "onClickableContentClick", "Landroid/widget/TextView;", "clickableContent", "signInWithGoogle", "Lcom/google/android/gms/common/SignInButton;", "method", "Lkotlin/Function0;", "app_debug"})
public final class BindingAdaptersKt {
    
    @androidx.databinding.BindingAdapter(value = {"android:onClick"})
    public static final void signInWithGoogle(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.common.SignInButton view, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> method) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"app:clickableContent"})
    public static final void onClickableContentClick(@org.jetbrains.annotations.NotNull()
    android.widget.TextView view, @org.jetbrains.annotations.NotNull()
    java.lang.String clickableContent) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"app:ifFormValid"})
    public static final void checkIfFormValid(@org.jetbrains.annotations.NotNull()
    android.widget.Button view, boolean validation) {
    }
    
    @androidx.databinding.BindingAdapter(requireAll = true, value = {"app:validation", "app:errorMessage"})
    public static final void checkIfValid(@org.jetbrains.annotations.NotNull()
    com.google.android.material.textfield.TextInputLayout view, boolean validation, @org.jetbrains.annotations.NotNull()
    java.lang.String errorMessage) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"app:textWatcher"})
    public static final void addTextWatcher(@org.jetbrains.annotations.NotNull()
    com.google.android.material.textfield.TextInputEditText view, @org.jetbrains.annotations.NotNull()
    android.text.TextWatcher textWatcher) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"app:loadImage"})
    public static final void loadImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView view, @org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap bitmap) {
    }
}