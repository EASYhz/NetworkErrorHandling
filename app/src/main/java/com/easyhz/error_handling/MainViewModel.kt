package com.easyhz.error_handling

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyhz.error_handling.domain.repository.MainRepository
import com.easyhz.error_handling.network.error.AppError
import com.easyhz.error_handling.network.error.HttpError
import com.easyhz.error_handling.network.response.MainResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel() {


    private val _resultState: MutableStateFlow<MainResponse?> = MutableStateFlow(MainResponse("null", "아직 실행 안 함"))
    val resultState: StateFlow<MainResponse?>
        get() = _resultState.asStateFlow()

    private val _errorState: MutableStateFlow<String> = MutableStateFlow("")
    val errorState: StateFlow<String>
        get() = _errorState.asStateFlow()

    fun getSuccess() = viewModelScope.launch {
        mainRepository.fetchSuccess()
            .onSuccess {
                _resultState.value = it
            }
            .onFailure {
                _errorState.value = it.handleError()
                _resultState.value = null
            }
    }

    fun getBadRequest() = viewModelScope.launch {
        mainRepository.fetchBadRequest()
            .onSuccess {
                _resultState.value = it
            }
            .onFailure {
                _errorState.value = it.handleError()
                _resultState.value = null
            }
    }

    fun getInternalServerError() = viewModelScope.launch {
        mainRepository.fetchInternalServerError()
            .onSuccess {
                _resultState.value = it
            }
            .onFailure {
                _errorState.value = it.handleError()
                _resultState.value = null
            }
    }

    fun getNotFound() = viewModelScope.launch {
        mainRepository.fetchNotFound()
            .onSuccess {
                _resultState.value = it
            }
            .onFailure {
                _errorState.value = it.handleError()
                _resultState.value = null
            }
    }

    fun getNotSupportedStatusCode() = viewModelScope.launch {
        mainRepository.fetchNotSupportedStatusCode()
            .onSuccess {
                _resultState.value = it
            }
            .onFailure {
                _errorState.value = it.handleError()
                _resultState.value = null
            }
    }

    fun getRealNotFound() = viewModelScope.launch {
        mainRepository.fetchRealNotFound()
            .onSuccess {
                _resultState.value = it
            }
            .onFailure {
                _errorState.value = it.handleError()
                _resultState.value = null
            }
    }

    private fun Throwable.handleError(): String {
        return when(this) {
            is AppError.UnexpectedError -> "예상치 못한 에러입니다.."
            is AppError.NetworkError -> "네트워크 에러가 떴어요.."
            is HttpError.BadRequestError -> "bad request"
            is HttpError.UnauthorizedError -> "unauthorized"
            is HttpError.ForbiddenError -> "Forbidden"
            is HttpError.NotFoundError -> "Not Found"
            is HttpError.InternalServerError -> "Internal Server Error"
            else -> "과연 .."
        }
    }
}