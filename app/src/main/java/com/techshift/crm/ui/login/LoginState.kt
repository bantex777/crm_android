package com.techshift.crm.ui.login

import com.techshift.crm.data.model.LoginResponse

data class LoginState(
    val isLoading: Boolean = false,
    val loginResponse: LoginResponse? = null,
    val error: String? = null
)
