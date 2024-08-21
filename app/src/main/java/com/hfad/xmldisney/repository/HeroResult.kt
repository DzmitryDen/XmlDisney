package com.hfad.xmldisney.repository


sealed class HeroResult {
    data class Success<T>(val data: T) : HeroResult()

    data class Error(val throwable: Throwable) : HeroResult()
}