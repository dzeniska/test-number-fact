package com.dzenis_ska.interesting_facts_about_numbers.utils

import androidx.fragment.app.Fragment
import com.dzenis_ska.interesting_facts_about_numbers.fragments.Fact

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {
    fun goToFactFragment(fact: Fact)
}