package com.example.kmmsocialmediaapp.android.common.util

private const val CURRENT_BASE_URL = "htt://192.168.0.107:8080/"

fun String.toCurrentUrl(): String {
    return "$CURRENT_BASE_URL${this.substring(26)}"
}