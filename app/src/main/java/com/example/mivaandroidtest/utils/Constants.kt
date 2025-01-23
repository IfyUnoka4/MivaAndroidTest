package com.example.mivaandroidtest.utils

import com.example.mivaandroidtest.R
import com.example.mivaandroidtest.data.datasources.remote.model.Subject
import com.example.mivaandroidtest.ui.theme.BlurOrange
import com.example.mivaandroidtest.ui.theme.DeepBlue
import com.example.mivaandroidtest.ui.theme.DeepPink
import com.example.mivaandroidtest.ui.theme.Green
import com.example.mivaandroidtest.ui.theme.LightBlue
import com.example.mivaandroidtest.ui.theme.Orange
import com.example.mivaandroidtest.ui.theme.Purple
import com.example.mivaandroidtest.ui.theme.Red
import com.example.mivaandroidtest.ui.theme.Yellow

const val BASE_URL = "https://private-8af5e5-mobileappconsultant.apiary-mock.com/"
const val DATABASE_NAME = "miva_db"

// Network Messages
const val SOCKET_TIME_OUT_EXCEPTION = "Request timed out while trying to connect. Please ensure you have a strong signal and try again."
const val UNKNOWN_NETWORK_EXCEPTION = "An unexpected error has occurred. Please check your network connection and try again."
const val CONNECT_EXCEPTION = "Could not connect to the server. Please check your internet connection and try again."
const val UNKNOWN_HOST_EXCEPTION = "Couldn't connect to the server at the moment. Please try again in a few minutes."
const val SSL_EXCEPTION = "Software caused connection abort. Please check your internet connection and try again."

val subjectList = listOf(
    Subject("Mathematics", R.drawable.ic_maths, Orange),
    Subject("English", R.drawable.ic_english, DeepBlue),
    Subject("Biology", R.drawable.ic_biology, Green),
    Subject("Chemistry", R.drawable.ic_chemistry, BlurOrange),
    Subject("Physics", R.drawable.ic_physics, Purple),
    Subject("Government", R.drawable.ic_govt, Yellow),
    Subject("Accounting", R.drawable.ic_account, LightBlue),
    Subject("Economics", R.drawable.ic_econs, Red),
    Subject("Literature", R.drawable.ic_lit, DeepPink)
)