package com.jonathan.sample2024.di

import com.jonathan.sample2024.repository.countries.CountryRepository
import com.jonathan.sample2024.repository.countries.CountryRepositoryImpl
import org.koin.dsl.module

val countryModule = module {
    single<CountryRepository> { CountryRepositoryImpl() }
}
