package com.refaat.themoviesdb.ui.favorites

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.common.getTheRecyclerViewItemDecoration
import com.refaat.themoviesdb.common.getTheRecyclerViewLayoutManager
import com.refaat.themoviesdb.databinding.FragmentFavoritesBinding
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.ui.adapters.MovieAdapter
import com.refaat.themoviesdb.ui.adapters.MoviesLoadStateAdapter
import com.refaat.themoviesdb.ui.home.HomeFragmentDirections
import com.refaat.themoviesdb.ui.home.tabLayoutPages.nowPlaying.NowPlayingViewModel
import com.refaat.themoviesdb.ui.search.SearchFragmentDirections
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()
    private val adapter =
        MovieAdapter { selectedMovie: Movie -> handleTheSelectedMovie(selectedMovie) }

    private fun handleTheSelectedMovie(selectedMovie: Movie) {
        val direction: NavDirections =
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(selectedMovie)
        NavHostFragment.findNavController(this).navigate(direction)
    }


    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favorites_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setUpAdapter()
        viewModel.getAllFavoritesMovies()
        viewModel.resultFavorites.observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                adapter.submitData(PagingData.from(it))
                binding.txtEmptyResult.isVisible = it.isEmpty()
            }
        })

        return binding.root
    }


    private fun setUpAdapter() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = getTheRecyclerViewLayoutManager(this@FavoritesFragment.context)
            addItemDecoration(getTheRecyclerViewItemDecoration(30, true))
        }
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = MoviesLoadStateAdapter { retry() }
        )

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading

                // empty view
                if (adapter.itemCount < 1) {
                    recyclerView.isVisible = false
                    txtEmptyResult.isVisible = true
                } else {
                    txtEmptyResult.isVisible = false
                }
            }
        }

    }

    private fun retry() {
        adapter.retry()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {

                MaterialAlertDialogBuilder(this@FavoritesFragment.requireContext())
                    .setTitle("Delete all")
                    .setMessage("Are you sure you want to delete all!")
                    .setPositiveButton("Yes") { dialog, which ->
                        // Respond to positive button press
                        viewModel.deleteAllFavoritesMovies()
                    }.setCancelable(true)
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}