package com.dzenis_ska.interesting_facts_about_numbers.model.remote.entities

import com.dzenis_ska.interesting_facts_about_numbers.fragments.Fact

data class NumberFactResponseBody(
    val text: String,
    val number: Long,
    val found: Boolean,
    val type: String
) {
    fun toFact() = Fact(
        number = number,
        numberFact = text
    )
}

