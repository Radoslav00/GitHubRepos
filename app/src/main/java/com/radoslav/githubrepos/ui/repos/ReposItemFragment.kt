package com.radoslav.githubrepos.ui.repos

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.radoslav.githubrepos.R
import com.radoslav.githubrepos.databinding.FragmentReposBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReposItemFragment : Fragment(R.layout.fragment_repos) {
    private val viewModel by viewModels<ReposViewModel>()

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentReposBinding.bind(view)

        val adapter = GitReposAdapter()

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = ItemRepoLoadStateAdapter { adapter.retry() },
                footer = ItemRepoLoadStateAdapter { adapter.retry() }
            )
        }
        viewModel.repos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}