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

val habits: MutableList<Habit> = mutableListOf(
    Habit(
        "Бегать по утрам",
        "Нужно не лениться и заставить себя встать рано утром и побежать",
        "Высокий",
        "1 раз",
        "Каждый день",
        "Полезная"
    ), Habit(
        "Курить",
        "Пора бросать курить, отказываться от сигарет",
        "Средний",
        "Пару раз",
        "Несколько раз в неделю",
        "Вредная"
    )
)