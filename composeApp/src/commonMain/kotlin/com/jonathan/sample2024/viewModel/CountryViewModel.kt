package com.jonathan.sample2024.viewModel

import androidx.lifecycle.ViewModel
import com.jonathan.sample2024.repository.countries.CountryRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CountryViewModel: ViewModel(), KoinComponent {
    val countryRepository: CountryRepository by inject()

    suspend fun getCountryInfo(countryCode: String) = countryRepository.getCountryInfo(countryCode)
}