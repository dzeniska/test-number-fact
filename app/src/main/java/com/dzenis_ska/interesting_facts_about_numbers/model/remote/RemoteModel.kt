package com.dzenis_ska.interesting_facts_about_numbers.model.remote

import android.util.Log
import com.dzenis_ska.interesting_facts_about_numbers.fragments.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteModel {
    private val apiNumber = NumbersApi.create()

    suspend fun getFactOfNumber(number: String, callback: (Fact?) -> Unit )  {
        Log.d("!!!getFactOfNumber", "${number}")
        try {
            val request = apiNumber.getFactOfNumber(number)
            callback(request.toFact())
            Log.d("!!!getFactOfNumber", "${request}")
        } catch (e: Exception) {
            Log.d("!!!getFactOfNumber", "Error: ${e.message}")
            callback(null)
        }
    }

    suspend fun getFactOfRandomNumber(callback: (Fact?) -> Unit) {
        try {
            val request = apiNumber.getFactOfRandomNumber()
            callback(request.toFact())
            Log.d("!!!getFactOfNumber", "${request}")
        } catch (e: Exception) {
            Log.d("!!!getFactOfNumber", "Error: ${e.message}")
            callback(null)
        }
    }
}