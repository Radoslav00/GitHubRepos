package com.radoslav.githubrepos.ui.repos

import androidx.lifecycle.ViewModel
import com.radoslav.githubrepos.data.GitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReposViewModel @Inject constructor(
    private val repository: GitRepository
) : ViewModel() {
}