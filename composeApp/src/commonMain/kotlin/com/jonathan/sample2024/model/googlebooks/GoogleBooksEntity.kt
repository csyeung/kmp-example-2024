package com.jonathan.sample2024.model.googlebooks

import kotlinx.serialization.Serializable

@Serializable
data class GoogleBooksEntity(
    val kind: String,
    val totalItems: Int,
    val items: List<GoogleBooksItem>
)
