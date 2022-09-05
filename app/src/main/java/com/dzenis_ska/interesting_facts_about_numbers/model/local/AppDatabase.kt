package com.dzenis_ska.interesting_facts_about_numbers.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dzenis_ska.interesting_facts_about_numbers.model.local.entities.FactHistoryDbEntity

@Database(entities = [FactHistoryDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDataBase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fact_history_list.db"
                ).build()
                instance
            }
        }
    }
}