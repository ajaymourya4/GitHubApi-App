package com.ajaymourya.githubapi.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ajaymourya.githubapi.ResultActivity
import com.ajaymourya.githubapi.databinding.SearchFragmentBinding


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    // private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = SearchFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the ProfileViewModel
        binding.viewModel = viewModel

        viewModel.navigateToResultPage.observe(this, Observer { userId ->
            val intent = Intent(this.context, ResultActivity::class.java)
            intent.putExtra("userId", userId)
            startActivity(intent)
        })

        return binding.root
    }
}
