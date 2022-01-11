package io.github.italbytz.infrastructure.openmensa

data class Canteen (
    val id: Long,
    val name: String,
    val city: String,
    val address: String,
    val coordinates: List<Double>? = null
)
