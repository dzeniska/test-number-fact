package com.dzenis_ska.interesting_facts_about_numbers.model.local

import android.util.Log
import com.dzenis_ska.interesting_facts_about_numbers.fragments.Fact
import com.dzenis_ska.interesting_facts_about_numbers.model.local.entities.FactHistoryDbEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LocalModel(val dao: FactDao) {
    private val scope = CoroutineScope(Dispatchers.IO)
    fun addToHistory(fact: Fact) {
        scope.launch {
            dao.addToHistory(FactHistoryDbEntity.fromFact(fact))
        }
    }
    fun getAllFactsFromHistory(): Flow<List<FactHistoryDbEntity>> {
        val dao = dao.getAllFactsFromHistory()
        return dao
    }


}