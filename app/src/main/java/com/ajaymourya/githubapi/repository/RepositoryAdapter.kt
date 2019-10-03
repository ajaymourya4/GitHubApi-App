package com.ajaymourya.githubapi.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajaymourya.githubapi.databinding.ListItemBinding
import com.ajaymourya.githubapi.network.Repository


/**
 * Created by Ajay Mourya on 02,October,2019
 */

class RepositoryAdapter(val clickListener: RepoClickListener) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var data = listOf<Repository>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]

        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository, clickListener: RepoClickListener) {
            binding.repository = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = ListItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }
}

class RepoClickListener(val clickListener: (repoUrl: String) -> Unit) {
    fun onClick(repo: Repository) = clickListener(repo.repoUrl)
}


