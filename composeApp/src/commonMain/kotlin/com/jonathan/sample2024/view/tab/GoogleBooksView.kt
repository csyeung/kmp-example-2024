package com.jonathan.sample2024.view.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.jonathan.sample2024.viewModel.GoogleBooksViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GoogleBooksView() {
    val viewModel: GoogleBooksViewModel = koinViewModel()
    val display by viewModel.books.collectAsState()

    // Call api on screen creation
    LaunchedEffect(true) {
        viewModel.getBooksInfo()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = display?.kind ?: "No data")
    }
}