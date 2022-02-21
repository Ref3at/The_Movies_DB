package com.refaat.themoviesdb.ui.home.tabLayoutPages.Popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import com.refaat.themoviesdb.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val useCases: UseCases) :
    ViewModel() {

    var hasLoadingError: Boolean = false

    var resultPopular: Flow<PagingData<Movie>>? = null


    init {
        val newResult: Flow<PagingData<Movie>> =
            useCases.getPopularUseCase().cachedIn(viewModelScope)
        resultPopular = newResult
    }

}