package com.dzenis_ska.interesting_facts_about_numbers

import android.app.Application
import com.dzenis_ska.interesting_facts_about_numbers.model.local.AppDatabase
import com.dzenis_ska.interesting_facts_about_numbers.model.local.LocalModel
import com.dzenis_ska.interesting_facts_about_numbers.model.remote.RemoteModel

class App: Application() {
    val remoteModel by lazy {
        RemoteModel()
    }
    val database by lazy { AppDatabase.getDataBase(this)}

    val localModel by lazy {
        LocalModel(database.factDao())
    }
}