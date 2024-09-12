package com.jonathan.sample2024

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform