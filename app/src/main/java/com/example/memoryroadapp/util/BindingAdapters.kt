package com.example.memoryroadapp.util

import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.memoryroadapp.SignUpActivity
import com.google.android.gms.common.SignInButton

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
    view.movementMethod = LinkMovementMethod.getInstance()
}