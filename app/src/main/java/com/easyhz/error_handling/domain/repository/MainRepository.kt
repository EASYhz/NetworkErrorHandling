package com.easyhz.error_handling.domain.repository

import com.easyhz.error_handling.network.response.MainResponse

interface MainRepository {

    suspend fun fetchSuccess(): Result<MainResponse>

    suspend fun fetchBadRequest(): Result<MainResponse>

    suspend fun fetchInternalServerError(): Result<MainResponse>

    suspend fun fetchNotFound(): Result<MainResponse>

    suspend fun fetchNotSupportedStatusCode(): Result<MainResponse>

    suspend fun fetchRealNotFound(): Result<MainResponse>
}