package com.jonathan.sample2024.model.countries

data class CountryEntity(
    val emoji: String,
    val name: String,
    val native: String,
    val capital: String?,
    val languages: List<LanguageEntity>,
)
