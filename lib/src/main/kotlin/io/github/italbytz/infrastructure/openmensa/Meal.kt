package io.github.italbytz.infrastructure.openmensa

data class Meal (
    val id: Long,
    val name: String,
    val category: String,
    val prices: Prices,
    val notes: List<String>
)

data class Prices (
    val students: Double? = null,
    val employees: Double? = null,
    val pupils: Double? = null,
    val others: Double? = null
)
