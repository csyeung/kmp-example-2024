package com.jonathan.sample2024.model.kakomon

data class MaintenanceEntity(
    val isMaintenance: Boolean,
    val start_datetime: String,
    val end_datetime: String,
    val message: String?
)
