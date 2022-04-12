package com.radoslav.githubrepos.api

import com.radoslav.githubrepos.data.GitRepoItem

data class GitResponse(
    val items: List<GitRepoItem>
)