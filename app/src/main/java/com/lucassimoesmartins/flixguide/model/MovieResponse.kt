package com.lucassimoesmartins.flixguide.model

data class MovieResponse(
    val page: Int,
    val results: ArrayList<Movie>,
    val total_pages: Int,
    val total_results: Int
)