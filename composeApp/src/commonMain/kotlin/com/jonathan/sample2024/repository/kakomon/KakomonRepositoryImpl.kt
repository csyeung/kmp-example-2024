package com.jonathan.sample2024.repository.kakomon

import com.apollographql.apollo.ApolloClient
import com.jonathan.sample2024.graphql.kakomon.LaunchCheckQuery
import com.jonathan.sample2024.graphql.kakomon.type.VersionInput
import com.jonathan.sample2024.model.kakomon.LaunchCheckEntity
import com.jonathan.sample2024.model.kakomon.MaintenanceEntity

class KakomonRepositoryImpl: KakomonRepository {
    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://review.kakomon.me/graphql")
        .build()

    override suspend fun getMaintenanceInfo(
        jobTypeId: String
    ): LaunchCheckEntity {
        val response = apolloClient.query(LaunchCheckQuery(jobTypeId)).execute()
        val maintainInfo = response.dataAssertNoErrors.checkMaintenanceApp

        if (maintainInfo != null) {
            return LaunchCheckEntity(
                forceUpdate = false,
                maintenanceEntity = MaintenanceEntity(
                    isMaintenance = maintainInfo.isMaintenance,
                    start_datetime = "hello",
                    end_datetime = "hello",
                    //                    start_datetime = maintainInfo.start_datetime,
                    //                    end_datetime = maintainInfo.end_datetime,
                    message = maintainInfo.message
                )
            )
        } else return LaunchCheckEntity(
                            forceUpdate = true,
                            maintenanceEntity = MaintenanceEntity(
                                isMaintenance = false,
                                start_datetime = "2020-02-02 02:02",
                                end_datetime = "2022-12-12 12:12",
                                message = "HELLO"
                            )
                        )
    }
}