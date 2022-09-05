package com.dzenis_ska.interesting_facts_about_numbers.utils

import android.os.Build
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.setTranslucentStatusAndNavigation(isTranslucent: Boolean) {
    Log.d("!!!setTranslucentStatusAndNavigation", "$isTranslucent")

    if (Build.VERSION.SDK_INT in Build.VERSION_CODES.KITKAT until Build.VERSION_CODES.R) {
        if (isTranslucent)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        else
            window.clearFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        setTranslucent(isTranslucent)
    }
}