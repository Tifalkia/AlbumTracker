package com.example.albumtracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albumtracker.databinding.ItemAlbumBinding
import com.example.albumtracker.model.Album

class AlbumAdapter(
    albums: List<Album>,
    private val onAlbumClick: (Album) -> Unit
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private var albums: List<Album> = albums

    fun updateAlbums(newAlbums: List<Album>) {
        albums = newAlbums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    inner class AlbumViewHolder(
        private val binding: ItemAlbumBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) = with(binding) {
            tvTitle.text = album.title
            tvArtist.text = album.artist
            tvYear.text = album.year?.toString() ?: "Ano não informado"
            tvStatus.text = album.status.label
            tvRating.text = buildRating(album.rating)
            tvFavorite.visibility = if (album.isFavorite) View.VISIBLE else View.GONE
            ivAlbum.setBackgroundResource(album.accentColorRes)

            root.setOnClickListener {
                onAlbumClick(album)
            }
        }
    }

    private fun buildRating(rating: Int): String {
        val safeRating = rating.coerceIn(0, 5)
        return "★".repeat(safeRating) + "☆".repeat(5 - safeRating)
    }
}
