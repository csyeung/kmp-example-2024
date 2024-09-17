package com.jonathan.sample2024.view.tab

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.jonathan.sample2024.model.CountryEntity
import com.jonathan.sample2024.viewModel.CountryViewModel
import kotlinx.coroutines.runBlocking
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CountrySearchView() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val viewModel: CountryViewModel = koinViewModel()
    val country by viewModel.country.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("地域コードを入力してください") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                runBlocking {
                    viewModel.getCountryInfo(searchText.text)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))

        country?.let {
            CountryResultView(country = it)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CountryResultView(country: CountryEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = country.emoji,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = country.name,
            style = MaterialTheme.typography.h4
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = country.native,
            style = MaterialTheme.typography.h6
        )
    }

    country.capital?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "首都: $it",
                style = MaterialTheme.typography.h6
            )
        }
    }

    country.languages.forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = "言語: ${it.name}",
                style = MaterialTheme.typography.h6
            )
        }
    }
}
