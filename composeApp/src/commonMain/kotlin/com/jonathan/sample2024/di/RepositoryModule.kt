package com.jonathan.sample2024.di

import com.jonathan.sample2024.repository.countries.CountryRepository
import com.jonathan.sample2024.repository.countries.CountryRepositoryImpl
import com.jonathan.sample2024.repository.googlebooks.GoogleBooksRepository
import com.jonathan.sample2024.repository.googlebooks.GoogleBooksRepositoryImpl
import com.jonathan.sample2024.repository.kakomon.KakomonRepository
import com.jonathan.sample2024.repository.kakomon.KakomonRepositoryImpl
import com.jonathan.sample2024.viewModel.CountryViewModel
import com.jonathan.sample2024.viewModel.KakomonViewModel
import com.jonathan.sample2024.viewModel.GoogleBooksViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val countryModule = module {
    single<CountryRepository> { CountryRepositoryImpl() }
    single<KakomonRepository> { KakomonRepositoryImpl() }
    single<GoogleBooksRepository> { GoogleBooksRepositoryImpl() }
}

val viewModelModule = module {
    viewModelOf(::CountryViewModel)
    viewModelOf(::KakomonViewModel)
    viewModelOf(::GoogleBooksViewModel)
}
