package com.easyhz.error_handling.network.api

import com.easyhz.error_handling.network.response.MainResponse
import retrofit2.http.GET

interface MainService {

    /**
     * 정상 api
     */
    @GET("/http/200/success")
    suspend fun fetchSuccess(): Result<MainResponse>

    /**
     * 400 error
     */
    @GET("/http/400/bad_request")
    suspend fun fetchBadRequest(): Result<MainResponse>


    /**
     * 500 error
     */
    @GET("/http/500/server_error")
    suspend fun fetchInternalServerError(): Result<MainResponse>

    /**
     * 404 error
     */
    @GET("/http/404/not_found")
    suspend fun fetchNotFound(): Result<MainResponse>

    /**
     * not supported status
     */
    @GET("/http/77/not_supported")
    suspend fun fetchNotSupportedStatusCode(): Result<MainResponse>

    /**
     * real not found
     */
    @GET("/error/404")
    suspend fun fetchRealNotFound(): Result<MainResponse>

}