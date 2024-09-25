package com.jonathan.sample2024.repository.googlebooks

import com.jonathan.sample2024.model.googlebooks.GoogleBooksEntity
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class GoogleBooksRepositoryImpl : GoogleBooksRepository {
    override suspend fun getGoogleBooksInfo(): GoogleBooksEntity {
        val response = HttpClient().get("https://www.googleapis.com/books/v1/volumes?q={KMM}")
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromString<GoogleBooksEntity>(response.bodyAsText())
    }
}