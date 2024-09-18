package com.jonathan.sample2024.repository.kakomon

import com.jonathan.sample2024.model.kakomon.LaunchCheckEntity

interface KakomonRepository {
    suspend fun getMaintenanceInfo(jobTypeId: String): LaunchCheckEntity
}