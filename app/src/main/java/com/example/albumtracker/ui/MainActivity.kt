package com.example.albumtracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albumtracker.R
import com.example.albumtracker.data.MockAlbumRepository
import com.example.albumtracker.databinding.ActivityMainBinding
import com.example.albumtracker.model.Album

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var albumAdapter: AlbumAdapter
    private val allAlbums = MockAlbumRepository.getAlbums()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumAdapter = AlbumAdapter(allAlbums, ::openAlbumDetails)

        binding.recyclerAlbums.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = albumAdapter
        }

        binding.btnAll.setOnClickListener {
            showAllAlbums()
        }

        binding.btnFavorites.setOnClickListener {
            showFavoriteAlbums()
        }

        showAllAlbums()
    }

    private fun showAllAlbums() {
        albumAdapter.updateAlbums(allAlbums)
        updateAlbumCount(allAlbums.size)
        binding.btnAll.isEnabled = false
        binding.btnFavorites.isEnabled = true
    }

    private fun showFavoriteAlbums() {
        val favorites = allAlbums.filter { it.isFavorite }
        albumAdapter.updateAlbums(favorites)
        updateAlbumCount(favorites.size)
        binding.btnAll.isEnabled = true
        binding.btnFavorites.isEnabled = false
    }

    private fun updateAlbumCount(count: Int) {
        binding.tvAlbumCount.text = getString(R.string.album_count, count)
    }

    private fun openAlbumDetails(album: Album) {
        val intent = Intent(this, AlbumDetailActivity::class.java).apply {
            putExtra(AlbumDetailActivity.EXTRA_ID, album.id)
            putExtra(AlbumDetailActivity.EXTRA_TITLE, album.title)
            putExtra(AlbumDetailActivity.EXTRA_ARTIST, album.artist)
            putExtra(AlbumDetailActivity.EXTRA_GENRE, album.genre)
            putExtra(AlbumDetailActivity.EXTRA_RATING, album.rating)
            putExtra(AlbumDetailActivity.EXTRA_STATUS, album.status.name)
            putExtra(AlbumDetailActivity.EXTRA_FAVORITE, album.isFavorite)
            putExtra(AlbumDetailActivity.EXTRA_ACCENT_COLOR, album.accentColorRes)

            album.year?.let { putExtra(AlbumDetailActivity.EXTRA_YEAR, it) }
            album.review?.let { putExtra(AlbumDetailActivity.EXTRA_REVIEW, it) }
        }

        startActivity(intent)
    }
}
