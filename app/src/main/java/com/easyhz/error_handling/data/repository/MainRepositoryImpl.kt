package com.easyhz.error_handling.data.repository

import com.easyhz.error_handling.domain.repository.MainRepository
import com.easyhz.error_handling.network.api.MainService
import com.easyhz.error_handling.network.response.MainResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
): MainRepository {
    override suspend fun fetchSuccess(): Result<MainResponse> = mainService.fetchSuccess()

    override suspend fun fetchBadRequest(): Result<MainResponse> = mainService.fetchBadRequest()

    override suspend fun fetchInternalServerError(): Result<MainResponse> = mainService.fetchInternalServerError()

    override suspend fun fetchNotFound(): Result<MainResponse> = mainService.fetchNotFound()

    override suspend fun fetchNotSupportedStatusCode(): Result<MainResponse> = mainService.fetchNotSupportedStatusCode()

    override suspend fun fetchRealNotFound(): Result<MainResponse> = mainService.fetchRealNotFound()
}