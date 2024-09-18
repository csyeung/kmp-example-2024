package com.jonathan.sample2024.viewModel

import androidx.lifecycle.ViewModel
import com.jonathan.sample2024.model.kakomon.LaunchCheckEntity
import com.jonathan.sample2024.repository.kakomon.KakomonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KakomonViewModel : ViewModel(), KoinComponent {
    val kakomonRepository: KakomonRepository by inject()
    val _launchCheck = MutableStateFlow<LaunchCheckEntity?>(null)
    val launchCheck: StateFlow<LaunchCheckEntity?> = _launchCheck

    suspend fun getMaintenanceInfo(jobTypeId: String) {
        try {
            _launchCheck.value = kakomonRepository.getMaintenanceInfo(
                jobTypeId = jobTypeId
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}