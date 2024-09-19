package com.jonathan.sample2024.model.kakomon

data class LaunchCheckEntity(
    val forceUpdate: Boolean,
    val maintenanceEntity: MaintenanceEntity,
    val validateMail: Boolean,
    val validatePhone: Boolean,
    val jobTypeData: List<JobTypeEntity>
)
