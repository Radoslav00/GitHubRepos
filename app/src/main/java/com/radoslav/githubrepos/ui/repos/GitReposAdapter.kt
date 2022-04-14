package com.radoslav.githubrepos.ui.repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.radoslav.githubrepos.R
import com.radoslav.githubrepos.data.GitRepoItem
import com.radoslav.githubrepos.databinding.ItemGithubRepoBinding

class GitReposAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GitRepoItem, GitReposAdapter.RepoViewHolder>(REPO_COMPARATOR) {
    //az sum nai golemiq lamer

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemGithubRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class RepoViewHolder(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if(item != null){
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(repo: GitRepoItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(repo.owner.avatar_url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_android_black)
                    .into(ownerAvatar)

                textRepoName.text = repo.name
                textOwnerName.text = repo.owner.login
                textLanguage.text = "Language: " + repo.language
                textWatchersCount.text = "Watchers: " + repo.watchers_count.toString()
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(repo: GitRepoItem)
    }


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GitRepoItem>() {
            override fun areItemsTheSame(oldItem: GitRepoItem, newItem: GitRepoItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GitRepoItem, newItem: GitRepoItem) =
                oldItem == newItem
        }
    }
}
