package com.refaat.themoviesdb.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refaat.themoviesdb.common.Resource
import com.refaat.themoviesdb.domain.model.MovieDetail
import com.refaat.themoviesdb.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCases: UseCases) :
    ViewModel() {


    val theMovieDetail: MutableLiveData<Resource<MovieDetail>> = MutableLiveData()


    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            useCases.getMovieDetailUseCase(movieId).collect {
                when (it) {
                    is Resource.Loading -> {
                        theMovieDetail.value = Resource.Loading()
                    }
                    is Resource.Error -> {
                        theMovieDetail.value = Resource.Error(message = it.message)
                    }
                    is Resource.Success -> {
                        theMovieDetail.value = Resource.Success(it.data)
                    }
                }
            }
        }
    }


}