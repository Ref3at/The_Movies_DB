package com.refaat.themoviesdb.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.ItemMovieBinding
import com.refaat.themoviesdb.domain.model.Movie

class MovieAdapter(private val selectedMovie: (Movie) -> Unit) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MOVIE_COMPARATOR) {

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie?) {
            if (movie == null) {
                val resources = itemView.resources
                binding.txtTitle.text = resources.getString(R.string.loading)
                binding.txtReleaseDate.text = resources.getString(R.string.unknown)
                binding.txtRatingCount.text = resources.getString(R.string.unknown)
            } else {
                showMovieData(movie)
            }
        }

        private fun showMovieData(movie: Movie) {

            binding.txtTitle.text = movie.title
            binding.txtReleaseDate.text = movie.releaseDate
            binding.txtRatingCount.text = movie.ratingCount
            binding.txtGenre.text = movie.genreString

            Glide.with(itemView.context)
                .load(movie.posterImage)
                .placeholder(R.drawable.ic_image_placeholder_24)
                .error(R.drawable.ic_image_placeholder_24)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imgPoster)


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItem = getItem(position)
        if (movieItem != null) {
            holder.bind(movieItem)
            holder.itemView.setOnClickListener {
                selectedMovie.invoke(movieItem)
            }
        }


    }


    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }
}