package com.radoslav.githubrepos.data

import com.radoslav.githubrepos.api.GitAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepository @Inject constructor(private val gitApi: GitAPI) {
}