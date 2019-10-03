package com.ajaymourya.githubapi.repository

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ajaymourya.githubapi.databinding.RepositoryFragmentBinding


class RepositoryFragment(userId: String) : Fragment() {

    private var userId = userId

    private val viewModel: RepositoryViewModel by lazy {
        ViewModelProviders.of(this).get(RepositoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = RepositoryFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel

        val adapter = RepositoryAdapter(RepoClickListener { repoUrl ->

            val builder = CustomTabsIntent.Builder().build()
            builder.launchUrl(this.context, Uri.parse("https://github.com/$repoUrl"))
        })

        binding.recyclerView.adapter = adapter

        viewModel.repos.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.data = it
            }
        })

        viewModel.userId = userId

        return binding.root
    }
}
