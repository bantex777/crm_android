package com.techshift.crm.data.model

data class LoginResponse(
    val token: String,
    val userName: String,
    val role: String
)