package com.example.kmmsocialmediaapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform