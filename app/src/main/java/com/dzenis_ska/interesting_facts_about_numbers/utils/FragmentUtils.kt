package com.dzenis_ska.interesting_facts_about_numbers.utils

import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment

fun Fragment.toast(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun View.isFocus(): Boolean {
    return isFocused
}

fun View.hide() {
    isVisible = false
}
fun View.show() {
    isVisible = true
}