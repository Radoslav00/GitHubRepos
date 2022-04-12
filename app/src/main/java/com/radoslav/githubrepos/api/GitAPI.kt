package com.radoslav.githubrepos.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GitAPI {

    @GET("search/repositories")
    suspend fun searchRepos (
        @Query ("q") query: String,
        @Query ("page") page: Int,
        @Query ("per_page") perPage: Int
    ) : GitResponse
}