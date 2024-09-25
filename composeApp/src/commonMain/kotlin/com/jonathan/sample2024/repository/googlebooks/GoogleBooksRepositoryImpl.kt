package com.jonathan.sample2024.repository.googlebooks

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class GoogleBooksRepositoryImpl : GoogleBooksRepository {
    override suspend fun getGoogleBooksInfo(): String {
        val response = HttpClient().get("https://www.googleapis.com/books/v1/volumes?q={KMM}")
        return response.bodyAsText()
    }
}