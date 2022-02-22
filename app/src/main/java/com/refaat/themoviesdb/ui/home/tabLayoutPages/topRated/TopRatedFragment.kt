package com.refaat.themoviesdb.ui.home.tabLayoutPages.topRated

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.common.getTheRecyclerViewItemDecoration
import com.refaat.themoviesdb.common.getTheRecyclerViewLayoutManager
import com.refaat.themoviesdb.databinding.FragmentHomeBinding
import com.refaat.themoviesdb.databinding.FragmentTopRatedBinding
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.ui.adapters.MovieAdapter
import com.refaat.themoviesdb.ui.adapters.MoviesLoadStateAdapter
import com.refaat.themoviesdb.ui.home.HomeFragmentDirections
import com.refaat.themoviesdb.ui.home.tabLayoutPages.Popular.PopularViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedFragment : Fragment() {


    private val viewModel: TopRatedViewModel by viewModels()
    private val adapter =
        MovieAdapter { selectedMovie: Movie -> handleTheSelectedMovie(selectedMovie) }
    private fun handleTheSelectedMovie(selectedMovie: Movie) {
        val direction: NavDirections =
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(selectedMovie)
        NavHostFragment.findNavController(this).navigate(direction)
    }

    private var _binding: FragmentTopRatedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        setUpAdapter()
        lifecycleScope.launch {
            viewModel.resultTopRated?.collectLatest {
                adapter.submitData(it)
            }
        }
        if (viewModel.hasLoadingError){
            retry()
        }

        return binding.root
    }


    private fun setUpAdapter() {

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = getTheRecyclerViewLayoutManager(this@TopRatedFragment.context)
            addItemDecoration(getTheRecyclerViewItemDecoration(30,true))
        }
        binding.recyclerView.adapter = adapter.withLoadStateFooter(
            footer = MoviesLoadStateAdapter { retry() }
        )


        binding.retryButton.setOnClickListener { retry() }



        lifecycleScope.launch {
            adapter.addLoadStateListener { loadState ->
//                val isListEmpty =
//                    loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                val isListEmpty =
                    adapter.itemCount == 0

                // to call adapter.retry every time the fragment is opened
                // if there error at initial loading
                viewModel.hasLoadingError = isListEmpty

                // show empty list
                binding.txtError.isVisible = isListEmpty
                // Only show the list if refresh succeeds.
                binding.recyclerView.isVisible = !isListEmpty
                // Show loading spinner during initial load or refresh.
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                // Show the retry state if initial load or refresh fails.
                binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

                // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error

                errorState?.let {
                    binding.txtError.text = "\uD83D\uDE28 ${it.error.localizedMessage}"
                    viewModel.hasLoadingError = true


                }
            }
        }
    }

    private fun retry() {
        adapter.retry()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.hasLoadingError){
            retry()
        }
    }
}