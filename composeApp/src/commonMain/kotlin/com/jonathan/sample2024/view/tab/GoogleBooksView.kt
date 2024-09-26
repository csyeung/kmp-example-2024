package com.jonathan.sample2024.view.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.jonathan.sample2024.model.googlebooks.GoogleBooksItem
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
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        display?.let { data ->
            Text(text = data.kind)
            Text(text = "総数：${data.totalItems}")
        }

        display?.let { item ->
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(item.items) { data ->
                    GoogleBooksItemView(item = data)
                }
            }
        }
    }
}

@Composable
fun GoogleBooksItemView(item: GoogleBooksItem) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row {
            item.volumeInfo.imageLinks?.let {
                AsyncImage(
                    modifier = Modifier.weight(0.3f),
                    model = it.thumbnail.replace("http://", "https://"),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
            Column(modifier = Modifier
                .weight(0.7f)
                .padding(start = 8.dp)
            ) {
                Text(text = item.volumeInfo.title)
                item.volumeInfo.authors?.let { Text(text = it.joinToString(", ")) }
                item.volumeInfo.publisher?.let { Text(text = it) }
                item.volumeInfo.description?.let {
                    Text(
                        text = it,
                        maxLines = 3,
                        overflow = TextOverflow.Clip
                    )
                }
                item.volumeInfo.pageCount?.let { Text(text = "ページ数：$it") }
            }
        }
    }
}
