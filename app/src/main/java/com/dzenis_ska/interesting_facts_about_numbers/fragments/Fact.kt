package com.dzenis_ska.interesting_facts_about_numbers.fragments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fact(
    val number: Long?,
    val numberFact: String?
): Parcelable {
    companion object {
        val DEFAULT = Fact (number = null, numberFact = null)
    }
}
