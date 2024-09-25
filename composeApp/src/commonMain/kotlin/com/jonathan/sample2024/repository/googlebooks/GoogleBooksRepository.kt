package com.jonathan.sample2024.repository.googlebooks

import com.jonathan.sample2024.model.googlebooks.GoogleBooksEntity

interface GoogleBooksRepository {
    suspend fun getGoogleBooksInfo(): GoogleBooksEntity
}
