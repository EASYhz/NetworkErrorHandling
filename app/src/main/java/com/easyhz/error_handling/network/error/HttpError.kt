package com.easyhz.error_handling.network.error

sealed class HttpError : AppError() {

    /**
     * 400 Bad Request
     */
    data class BadRequestError(override val message: String) : HttpError()

    /**
     * 401 Unauthorized
     */
    data class UnauthorizedError(override val message: String) : HttpError()

    /**
     * 403 Forbidden
     */
    data class ForbiddenError(override val message: String) : HttpError()

    /**
     * 404 Not Found
     */
    data class NotFoundError(override val message: String) : HttpError()

    /**
     * 500 Internal Server Error
     */
    data class InternalServerError(override val message: String) : HttpError()
}

fun getErrorByStatusCode(statusCode: Int, message: String): AppError {
    return when (statusCode) {
        400 -> HttpError.BadRequestError(message = message)
        401 -> HttpError.UnauthorizedError(message = message)
        403 -> HttpError.ForbiddenError(message = message)
        404 -> HttpError.NotFoundError(message = message)
        500 -> HttpError.InternalServerError(message = message)
        else -> AppError.UnexpectedError
    }
}