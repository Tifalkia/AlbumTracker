package com.example.albumtracker.data

import com.example.albumtracker.R
import com.example.albumtracker.model.Album
import com.example.albumtracker.model.AlbumStatus

object MockAlbumRepository {

    fun getAlbums(): List<Album> = listOf(
        Album(
            id = 1,
            title = "Nevermind",
            artist = "Nirvana",
            year = 1991,
            genre = "Grunge",
            rating = 5,
            review = "Direto, barulhento e cheio de músicas que ficaram marcadas na história do rock alternativo.",
            status = AlbumStatus.LISTENED,
            isFavorite = true,
            accentColorRes = R.color.album_blue
        ),
        Album(
            id = 2,
            title = "Dirt",
            artist = "Alice in Chains",
            year = 1992,
            genre = "Grunge / Alternative Metal",
            rating = 5,
            review = "Atmosfera pesada, harmonias vocais marcantes e uma sequência muito consistente.",
            status = AlbumStatus.LISTENED,
            isFavorite = true,
            accentColorRes = R.color.album_orange
        ),
        Album(
            id = 3,
            title = "Blue Weekend",
            artist = "Wolf Alice",
            year = 2021,
            genre = "Alternative Rock",
            rating = 5,
            review = "Mistura momentos delicados e explosivos sem perder identidade.",
            status = AlbumStatus.LISTENING,
            isFavorite = false,
            accentColorRes = R.color.album_purple
        ),
        Album(
            id = 4,
            title = "Rid of Me",
            artist = "PJ Harvey",
            year = 1993,
            genre = "Alternative Rock",
            rating = 4,
            review = null,
            status = AlbumStatus.WANT_TO_LISTEN,
            isFavorite = false,
            accentColorRes = R.color.album_red
        ),
        Album(
            id = 5,
            title = "Live Through This",
            artist = "Hole",
            year = 1994,
            genre = "Alternative Rock / Grunge",
            rating = 4,
            review = "Ótimo equilíbrio entre melodias fortes e guitarras agressivas.",
            status = AlbumStatus.LISTENED,
            isFavorite = true,
            accentColorRes = R.color.album_green
        ),
        Album(
            id = 6,
            title = "Demo sem data",
            artist = "Banda Independente",
            year = null,
            genre = "Indie Rock",
            rating = 3,
            review = null,
            status = AlbumStatus.WANT_TO_LISTEN,
            isFavorite = false,
            accentColorRes = R.color.album_gray
        )
    )
}
