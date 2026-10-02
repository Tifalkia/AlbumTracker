package com.example.albumtracker.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.albumtracker.R
import com.example.albumtracker.databinding.ActivityAlbumDetailBinding
import com.example.albumtracker.model.Album
import com.example.albumtracker.model.AlbumStatus

class AlbumDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailBinding
    private var displayedAlbum: Album? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayedAlbum = readAlbumFromIntent()

        val album = displayedAlbum
        if (album == null) {
            finish()
            return
        }

        showAlbum(album)

        binding.btnFavorite.setOnClickListener {
            val currentAlbum = displayedAlbum ?: return@setOnClickListener
            displayedAlbum = currentAlbum.copy(isFavorite = !currentAlbum.isFavorite)
            updateFavoriteButton(displayedAlbum!!.isFavorite)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun showAlbum(album: Album) = with(binding) {
        tvTitle.text = album.title
        tvArtist.text = album.artist
        tvYear.text = album.year?.toString() ?: getString(R.string.year_unknown)
        tvGenre.text = album.genre
        tvStatus.text = album.status.label
        tvRating.text = buildRating(album.rating)
        ivAlbum.setBackgroundResource(album.accentColorRes)

        if (album.review.isNullOrBlank()) {
            tvReviewLabel.visibility = View.GONE
            tvReview.visibility = View.GONE
        } else {
            tvReviewLabel.visibility = View.VISIBLE
            tvReview.visibility = View.VISIBLE
            tvReview.text = album.review
        }

        updateFavoriteButton(album.isFavorite)
    }

    private fun updateFavoriteButton(isFavorite: Boolean) {
        binding.btnFavorite.text = if (isFavorite) {
            getString(R.string.remove_favorite)
        } else {
            getString(R.string.add_favorite)
        }
    }

    private fun readAlbumFromIntent(): Album? {
        val title = intent.getStringExtra(EXTRA_TITLE) ?: return null
        val artist = intent.getStringExtra(EXTRA_ARTIST) ?: return null
        val genre = intent.getStringExtra(EXTRA_GENRE) ?: getString(R.string.genre_unknown)
        val review = intent.getStringExtra(EXTRA_REVIEW)
        val status = intent.getStringExtra(EXTRA_STATUS)
            ?.let { runCatching { AlbumStatus.valueOf(it) }.getOrNull() }
            ?: AlbumStatus.WANT_TO_LISTEN

        val year = if (intent.hasExtra(EXTRA_YEAR)) {
            intent.getIntExtra(EXTRA_YEAR, 0)
        } else {
            null
        }

        return Album(
            id = intent.getIntExtra(EXTRA_ID, -1),
            title = title,
            artist = artist,
            year = year,
            genre = genre,
            rating = intent.getIntExtra(EXTRA_RATING, 0),
            review = review,
            status = status,
            isFavorite = intent.getBooleanExtra(EXTRA_FAVORITE, false),
            accentColorRes = intent.getIntExtra(EXTRA_ACCENT_COLOR, R.color.album_gray)
        )
    }

    private fun buildRating(rating: Int): String {
        val safeRating = rating.coerceIn(0, 5)
        return "★".repeat(safeRating) + "☆".repeat(5 - safeRating)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_ARTIST = "extra_artist"
        const val EXTRA_YEAR = "extra_year"
        const val EXTRA_GENRE = "extra_genre"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_REVIEW = "extra_review"
        const val EXTRA_STATUS = "extra_status"
        const val EXTRA_FAVORITE = "extra_favorite"
        const val EXTRA_ACCENT_COLOR = "extra_accent_color"
    }
}
