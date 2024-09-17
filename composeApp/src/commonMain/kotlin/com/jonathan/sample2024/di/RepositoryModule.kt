package com.jonathan.sample2024.di

import com.jonathan.sample2024.repository.countries.CountryRepository
import com.jonathan.sample2024.repository.countries.CountryRepositoryImpl
import com.jonathan.sample2024.viewModel.CountryViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val countryModule = module {
    single<CountryRepository> { CountryRepositoryImpl() }
}

val viewModelModule = module {
    viewModelOf(::CountryViewModel)
}
