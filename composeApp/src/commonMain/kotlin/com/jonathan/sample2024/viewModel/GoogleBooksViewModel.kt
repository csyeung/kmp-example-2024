package com.jonathan.sample2024.viewModel

import androidx.lifecycle.ViewModel
import com.jonathan.sample2024.model.googlebooks.GoogleBooksEntity
import com.jonathan.sample2024.repository.googlebooks.GoogleBooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GoogleBooksViewModel: ViewModel(), KoinComponent {
    val repository: GoogleBooksRepository by inject()

    val _books = MutableStateFlow<GoogleBooksEntity?>(null)
    val books: StateFlow<GoogleBooksEntity?> = _books

    suspend fun getBooksInfo() {
        try {
            _books.value = repository.getGoogleBooksInfo()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}