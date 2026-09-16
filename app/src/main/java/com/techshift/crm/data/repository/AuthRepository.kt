package com.techshift.crm.data.repository

import com.techshift.crm.data.api.AuthApi
import com.techshift.crm.data.model.LoginRequest
import com.techshift.crm.data.model.LoginResponse

class AuthRepository(private val authApi: AuthApi) {

    suspend fun login(
        email: String,
        password: String
    ) : LoginResponse {

        val request = LoginRequest(email, password)

        return authApi.login(request)
    }
}