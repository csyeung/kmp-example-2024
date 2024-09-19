package com.jonathan.sample2024.repository.kakomon

import com.apollographql.apollo.ApolloClient
import com.jonathan.sample2024.graphql.kakomon.LaunchCheckQuery
import com.jonathan.sample2024.model.kakomon.JobTypeEntity
import com.jonathan.sample2024.model.kakomon.LaunchCheckEntity
import com.jonathan.sample2024.model.kakomon.MaintenanceEntity

class KakomonRepositoryImpl: KakomonRepository {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://review.kakomon.me/graphql")
        .build()

    override suspend fun getMaintenanceInfo(
        jobTypeId: String,
        mailAddress: String,
        phoneNumber: String
    ): LaunchCheckEntity {
        val response = apolloClient.query(LaunchCheckQuery(jobTypeId, mailAddress, phoneNumber)).execute()
        val info = response.dataAssertNoErrors
        val maintainInfo = info.checkMaintenanceApp
        val jobTypeList = info.jobTypes.map {
            JobTypeEntity(it.id, it.name, it.slug)
        }

        return LaunchCheckEntity(
            forceUpdate = false,
            maintenanceEntity = maintainInfo?.let {
                MaintenanceEntity(
                    isMaintenance = it.isMaintenance,
                    start_datetime = it.start_datetime.toString(),
                    end_datetime = it.end_datetime.toString(),
                    message = it.message
                )
            } ?: MaintenanceEntity(
                isMaintenance = false,
                start_datetime = "2020-02-02 02:02",
                end_datetime = "2022-12-12 12:12",
                message = "HELLO"
            ),
            validateMail = info.validateMail,
            validatePhone = info.validateTel,
            jobTypeData = jobTypeList
        )
    }
}