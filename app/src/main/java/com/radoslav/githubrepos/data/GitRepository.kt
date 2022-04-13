package com.radoslav.githubrepos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.radoslav.githubrepos.api.GitAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepository @Inject constructor(private val gitApi: GitAPI) {
    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 30,
                prefetchDistance = 15,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GitPagingSource(gitApi, query) }
        ).liveData
}