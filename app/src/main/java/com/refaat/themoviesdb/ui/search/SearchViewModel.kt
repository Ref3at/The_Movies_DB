package com.refaat.themoviesdb.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.domain.repository.TheMovieDbRepository
import com.refaat.themoviesdb.domain.useCases.GetMoviesOfSearchQueryUseCase
import com.refaat.themoviesdb.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCases: UseCases) :
    ViewModel() {

    private val currentQuery:MutableLiveData<String> = MutableLiveData()

    val result = currentQuery.switchMap { searchQuery ->
        useCases.getMoviesOfSearchQueryUseCase(searchQuery).cachedIn(viewModelScope)
    }

    fun getMoviesOfSearchQuery(searchQuery: String) {
        currentQuery.value = searchQuery
    }


}

