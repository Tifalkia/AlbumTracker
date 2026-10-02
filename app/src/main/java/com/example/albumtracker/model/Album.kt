package com.example.albumtracker.model

import androidx.annotation.ColorRes

enum class AlbumStatus(val label: String) {
    WANT_TO_LISTEN("Quero ouvir"),
    LISTENING("Ouvindo"),
    LISTENED("Ouvido")
}

data class Album(
    val id: Int,
    val title: String,
    val artist: String,
    val year: Int?,
    val genre: String,
    val rating: Int,
    val review: String?,
    val status: AlbumStatus,
    val isFavorite: Boolean = false,
    @ColorRes val accentColorRes: Int
)
