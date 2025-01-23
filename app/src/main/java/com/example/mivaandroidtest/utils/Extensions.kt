package com.example.mivaandroidtest.utils

import android.content.Context
import android.widget.Toast
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun String.encodeToUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())

fun String.decodeFromUrl(): String = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())

inline fun <reified T> String.decodeJsonString(): T = Json.decodeFromString(this)

inline fun <reified T> T.encodeToJson(): String = Json.encodeToString(this)
