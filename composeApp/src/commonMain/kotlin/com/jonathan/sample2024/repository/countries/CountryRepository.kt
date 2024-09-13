package com.jonathan.sample2024.repository.countries

import com.apollographql.apollo.ApolloClient
import com.jonathan.sample2024.model.*
import com.jonathan.sample2024.graphql.countries.CountryInfoQuery

class CountryRepository {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    suspend fun getCountryInfo(countryCode: String): CountryEntity {
        val response = apolloClient.query(query = CountryInfoQuery(code = countryCode)).execute()
        return response.dataAssertNoErrors.country?.let {
            CountryEntity(
                emoji = it.emoji,
                name = it.name,
                native = it.native,
                capital = it.capital,
                languages = it.languages.map { language ->
                    LanguageEntity(
                        code = language.code,
                        name = language.name,
                    )
                }
            )
        } ?: throw IllegalStateException("Country not found")
    }
}
