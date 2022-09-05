package com.dzenis_ska.interesting_facts_about_numbers.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dzenis_ska.interesting_facts_about_numbers.model.local.entities.FactHistoryDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {

    @Insert
    fun addToHistory(fact: FactHistoryDbEntity)

    @Query("SELECT * FROM facts")
    fun getAllFactsFromHistory(): Flow<List<FactHistoryDbEntity>>

    @Query("SELECT * FROM facts WHERE id = :id")
    suspend fun getFactFromHistory(id: Long): FactHistoryDbEntity

}