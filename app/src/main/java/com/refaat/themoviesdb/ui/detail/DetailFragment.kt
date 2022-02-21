package com.refaat.themoviesdb.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.common.Resource
import com.refaat.themoviesdb.databinding.FragmentDetailBinding
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.model.MovieDetail
import com.refaat.themoviesdb.ui.home.tabLayoutPages.Popular.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()


    private val viewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val theMovie = args.selectedMovie
        fillViewsFromMovie(theMovie)
        viewModel.theMovieDetail.observe(viewLifecycleOwner, Observer {

            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.GONE
                    binding.txtError.visibility = View.GONE
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.retryButton.visibility = View.VISIBLE
                    binding.txtError.visibility = View.VISIBLE
                    binding.txtError.text = it.message
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.retryButton.visibility = View.GONE
                    binding.txtError.visibility = View.GONE
                    fillViewsFromMovieDetail(it.data!!)
                }
            }


        })
        binding.retryButton.setOnClickListener {
            fetchTheMovieDetail(theMovie.id)
        }
        fetchTheMovieDetail(theMovie.id)


        return binding.root
    }

    private fun fetchTheMovieDetail(movieId: Int?) {
        viewModel.getMovieDetail(movieId!!)
    }

    private fun fillViewsFromMovie(theMovie: Movie) {
        Glide.with(this)
            .load(theMovie.backdropImage)
            .placeholder(R.drawable.ic_image_placeholder_24)
            .error(R.drawable.ic_image_placeholder_24)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.imgBackdrop)

        binding.txtTitle.text = theMovie.title
        binding.txtReleaseDate.text = theMovie.releaseDate
        binding.txtRatingCount.text = theMovie.ratingCount

    }


    private fun fillViewsFromMovieDetail(theMovieDetail: MovieDetail) {
        Glide.with(this)
            .load(theMovieDetail.backdropImage)
            .placeholder(R.drawable.ic_image_placeholder_24)
            .error(R.drawable.ic_image_placeholder_24)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.imgBackdrop)

        binding.txtTitle.text = theMovieDetail.title
        binding.txtReleaseDate.text = theMovieDetail.releaseDate
        binding.txtRatingCount.text = theMovieDetail.ratingCount
        binding.txtOverView.text = theMovieDetail.overview
        binding.txtGenre.text = theMovieDetail.genresString


        if (theMovieDetail.isFavorites){
            binding.btnFavorites.text = "Remove from favorites"
        }else{
            binding.btnFavorites.text = "Add to favorites"
        }
        binding.btnFavorites.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}