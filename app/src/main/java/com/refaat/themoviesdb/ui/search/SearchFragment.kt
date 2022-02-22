package com.refaat.themoviesdb.ui.search

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.paging.LoadState
import com.refaat.themoviesdb.common.getTheRecyclerViewItemDecoration
import com.refaat.themoviesdb.common.getTheRecyclerViewLayoutManager
import com.refaat.themoviesdb.databinding.FragmentSearchBinding
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.ui.adapters.MovieAdapter
import com.refaat.themoviesdb.ui.adapters.MoviesLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var currentSearchQuery: String? = null


    private val viewModel: SearchViewModel by viewModels()
    private val adapter =
        MovieAdapter { selectedMovie: Movie -> handleTheSelectedMovie(selectedMovie) }

    private fun handleTheSelectedMovie(selectedMovie: Movie) {
        val direction: NavDirections =
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(selectedMovie)
        findNavController(this).navigate(direction)
    }

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)


        setUpAdapter()

        viewModel.result.observe(viewLifecycleOwner) {
            binding.recyclerView.scrollToPosition(0)
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        binding.searchView.requestFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                getMoviesOfSearchQuery(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true

            }
        })



        return binding.root

    }


    fun getMoviesOfSearchQuery(searchQuery: String) {
        if (!currentSearchQuery.equals(searchQuery)) {
            currentSearchQuery = searchQuery
        } else {
            return
        }

        binding.recyclerView.removeAllViews()
        binding.searchView.clearFocus()
        viewModel.getMoviesOfSearchQuery(searchQuery)
    }


    private fun setUpAdapter() {

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = getTheRecyclerViewLayoutManager(this@SearchFragment.context)
            addItemDecoration(getTheRecyclerViewItemDecoration(30, true))
        }
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = MoviesLoadStateAdapter { retry() }
        )

        binding.retryButton.setOnClickListener { retry() }


        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                retryButton.isVisible = loadState.source.refresh is LoadState.Error

                // Show the error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error
                errorState?.let {
                    binding.txtError.text = "\uD83D\uDE28 ${it.error.localizedMessage}"
                }
                txtError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1) {
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}