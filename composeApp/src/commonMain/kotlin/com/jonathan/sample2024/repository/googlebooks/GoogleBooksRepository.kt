package com.jonathan.sample2024.repository.googlebooks

interface GoogleBooksRepository {
    suspend fun getGoogleBooksInfo(): String
}
