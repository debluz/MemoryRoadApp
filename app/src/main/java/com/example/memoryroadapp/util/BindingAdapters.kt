package com.example.memoryroadapp.util

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.memoryroadapp.ui.SignUpActivity
import com.google.android.gms.common.SignInButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("android:onClick")
fun signInWithGoogle(view: SignInButton, method: () -> Unit){
    view.setOnClickListener { method() }
}

@BindingAdapter("app:clickableContent")
fun onClickableContentClick(view: TextView, clickableContent: String){
    val text = view.text.toString()
    val context = view.context
    val spannableString = SpannableString(text)
    val start = text.indexOf(clickableContent)
    val end = start + clickableContent.length
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.color = Color.BLUE
            ds.isUnderlineText = true
        }
    }

    spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    view.text = spannableString
    view.movementMethod = LinkMovementMethod.getInstance()
}


@BindingAdapter("app:ifFormValid")
fun checkIfFormValid(view: Button, validation: Boolean){
    view.isEnabled = validation
}

@BindingAdapter(value = ["app:validation","app:errorMessage"], requireAll = true)
fun checkIfValid(view: TextInputLayout, validation: Boolean, errorMessage: String){
    if(!validation) {
        view.error = errorMessage
    } else {
        view.error = null
    }
}

@BindingAdapter("app:textWatcher")
fun addTextWatcher(view: TextInputEditText, textWatcher: TextWatcher){
    view.addTextChangedListener(textWatcher)
}

@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView, bitmap: Bitmap?){
    if(bitmap != null){
        view.setImageBitmap(bitmap)
    }
}