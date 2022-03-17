package com.example.habitstracker

data class Habit (
    var name: String,
    var description: String,
    var priority: String,
    var quantity: String,
    var period: String,
    var type: String
) : java.io.Serializable {
}

val habits: MutableList<Habit> = mutableListOf()