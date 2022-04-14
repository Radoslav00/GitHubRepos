package com.radoslav.githubrepos.ui.repos

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.radoslav.githubrepos.data.GitRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repository: GitRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    private val currentQuery = state.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val repos = currentQuery.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchRepos(query: String){
        currentQuery.value = query
    }

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val DEFAULT_QUERY = "hello world"
    }
}