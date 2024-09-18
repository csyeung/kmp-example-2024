package com.jonathan.sample2024.repository.countries

import com.jonathan.sample2024.model.countries.CountryEntity

interface CountryRepository {
    suspend fun getCountryInfo(countryCode: String): CountryEntity
}
