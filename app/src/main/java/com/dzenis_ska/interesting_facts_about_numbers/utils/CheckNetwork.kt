package com.dzenis_ska.interesting_facts_about_numbers.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.dzenis_ska.interesting_facts_about_numbers.R

object CheckNetwork {
    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.d("!!!capabilities", "TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.d("!!!capabilities", "TRANSPORT_WIFI")

                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.d("!!!capabilities", "TRANSPORT_ETHERNET")
                    return true
                }
                else -> false
            }
        } else {
            Log.d("!!!capabilities", "NO_INTERNET")
            Toast.makeText(context, context.getString(R.string.need_internet), Toast.LENGTH_SHORT).show()
            false
        }
    }
}
