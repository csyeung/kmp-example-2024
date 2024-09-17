package com.jonathan.sample2024.viewModel

import androidx.lifecycle.ViewModel
import com.jonathan.sample2024.model.CountryEntity
import com.jonathan.sample2024.repository.countries.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CountryViewModel: ViewModel(), KoinComponent {
    val countryRepository: CountryRepository by inject()

    val _country = MutableStateFlow<CountryEntity?>(null)
    val country: StateFlow<CountryEntity?> = _country

    suspend fun getCountryInfo(countryCode: String) {
        try {
            _country.value = countryRepository.getCountryInfo(countryCode)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
