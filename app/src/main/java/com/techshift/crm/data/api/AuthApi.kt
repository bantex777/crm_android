package com.techshift.crm.data.api

import com.techshift.crm.data.model.LoginRequest
import com.techshift.crm.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/users/auth")
    suspend fun login(
        @Body request: LoginRequest
    ) : LoginResponse
}