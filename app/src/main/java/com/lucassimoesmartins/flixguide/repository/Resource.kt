package com.lucassimoesmartins.flixguide.repository

class Resource<T>(
    val data: T?,
    val error: String? = null
)