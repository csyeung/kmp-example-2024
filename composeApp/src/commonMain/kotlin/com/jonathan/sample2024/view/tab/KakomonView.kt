package com.jonathan.sample2024.view.tab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.jonathan.sample2024.model.kakomon.LaunchCheckEntity
import com.jonathan.sample2024.viewModel.KakomonViewModel
import kotlinx.coroutines.runBlocking
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun KakomonView() {
    val viewModel: KakomonViewModel = koinViewModel()
    val launchEntity by viewModel.launchCheck.collectAsState()

    var jobTypeText by remember { mutableStateOf(TextFieldValue("4")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TextField(
            value = jobTypeText,
            onValueChange = { jobTypeText = it },
            label = { Text("職種コードを入力してください") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                runBlocking {
                    viewModel.getMaintenanceInfo(
                        jobTypeId = jobTypeText.text,
                        null,
                        null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))

        launchEntity?.let {
            LaunchEntityResultView(it)
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun LaunchEntityResultView(entity: LaunchCheckEntity) {
    if (entity.forceUpdate) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Force Update",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(entity.jobTypeData) { jobType ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = jobType.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = jobType.id,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(
                    text = jobType.slug,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}