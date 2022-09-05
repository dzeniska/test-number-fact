package com.dzenis_ska.interesting_facts_about_numbers.utils

class Event<T>(
    private val value: T
) {
    private var handled: Boolean = false

    fun getValue(): T? {
        if (handled) return null
        handled = true
        return value
    }
}