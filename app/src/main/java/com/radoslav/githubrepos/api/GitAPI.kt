package com.radoslav.githubrepos.api

import com.radoslav.githubrepos.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.*

interface GitAPI {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val ACCESS_TOKEN = BuildConfig.GITHUB_ACCESS_KEY
    }

    @Headers(
        "Accept: application/vnd.github.v3+json",
        //"Authorization: token $ACCESS_TOKEN"
    )
    @GET("search/repositories")
    suspend fun searchRepos (
        @Query ("q") query: String,
        @Query ("page") page: Int,
        @Query ("per_page") perPage: Int
    ) : GitResponse
}