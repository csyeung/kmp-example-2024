package com.jonathan.sample2024.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonathan.sample2024.di.countryModule
import com.jonathan.sample2024.di.viewModelModule
import com.jonathan.sample2024.view.tab.CountrySearchView
import com.jonathan.sample2024.view.tab.KakomonView
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Preview
@Composable
fun MainView() {
    startKoin {
        modules(
            listOf(
                countryModule,
                viewModelModule
            )
        )
    }
    MaterialTheme {
        MainViewContent()
    }
}

@Composable
fun MainViewContent() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Country", "TODO 2", "TODO 3", "国試")

    Column(Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    height = 4.dp
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> CountrySearchView()
            1 -> TabContent("Content for Tab 2")
            2 -> TabContent("Content for Tab 3")
            3 -> KakomonView()
        }
    }
}

@Composable
fun TabContent(text: String) {
    Text(text, Modifier.fillMaxSize(), style = MaterialTheme.typography.h4)
}

@Preview
@Composable
fun MainViewPreview() {
    MainView()
}