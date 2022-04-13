package com.radoslav.githubrepos.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.radoslav.githubrepos.databinding.ItemGithubLoadStateFooterBinding

class ItemRepoLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<ItemRepoLoadStateAdapter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemGithubLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class LoadStateViewHolder(private val binding: ItemGithubLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener{
                retry.invoke()
            }
        }

            fun bind (loadState: LoadState) {
                binding.apply {
                    progressBar.isVisible = loadState is LoadState.Loading
                    buttonRetry.isVisible = loadState !is LoadState.Loading
                    textViewError.isVisible = loadState !is LoadState.Loading

                }
            }
    }
}