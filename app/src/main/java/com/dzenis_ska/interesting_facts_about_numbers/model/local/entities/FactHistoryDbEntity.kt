package com.dzenis_ska.interesting_facts_about_numbers.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dzenis_ska.interesting_facts_about_numbers.fragments.Fact

@Entity(tableName = "facts")
data class FactHistoryDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    val number: Long,
    val fact: String
) {

    fun toFact(): Fact = Fact(
        number = number,
        numberFact = fact
    )

    companion object {
        fun fromFact(fact: Fact) = FactHistoryDbEntity(
            id = 0,
            number = fact.number!!,
            fact = fact.numberFact!!
        )
    }
}