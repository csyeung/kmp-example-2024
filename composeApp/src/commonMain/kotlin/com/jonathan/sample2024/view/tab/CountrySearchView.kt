package com.jonathan.sample2024.view.tab

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CountrySearchView() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = { /* Handle search action */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // TODO: Replace with actual search results
        CountryResultView(countryName = "Country Name", countryFlag = "🇺🇸")
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CountryResultView(countryName: String, countryFlag: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = countryFlag,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = countryName,
            style = MaterialTheme.typography.h4
        )
    }
}
