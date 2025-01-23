package com.example.mivaandroidtest.utils

data class ApiResponse<T>(var code: Int = 0, var data: T?, var message: String? = "")