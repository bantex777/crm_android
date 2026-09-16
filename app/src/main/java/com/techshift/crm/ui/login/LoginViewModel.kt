package com.techshift.crm.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techshift.crm.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState =  MutableStateFlow(LoginState())

    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {

        viewModelScope.launch {
            _uiState.value = LoginState(isLoading = true)

            try {
                val response = authRepository.login(email, password)
                _uiState.value = LoginState(isLoading = false, loginResponse = response)
            } catch (e: Exception) {
                _uiState.value = LoginState(isLoading = false, error = e.message ?: "Login failed")
            }
        }
    }
}