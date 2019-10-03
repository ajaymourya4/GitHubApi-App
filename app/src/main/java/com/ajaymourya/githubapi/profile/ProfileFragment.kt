package com.ajaymourya.githubapi.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ajaymourya.githubapi.databinding.ProfileFragmentBinding
import com.ajaymourya.githubapi.search.SearchFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


class ProfileFragment(userId: String) : Fragment() {

    private var userId = userId

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = ProfileFragmentBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the ProfileViewModel
        binding.viewModel = viewModel

        viewModel.userId = userId

        viewModel.profileImgUrl.observe(this, Observer { imgUrl ->
            Glide
                .with(this)
                .load(imgUrl)
                .apply(
                    RequestOptions().override(
                        300,
                        300
                    ).diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                )
                .into(binding.statusImage)
        })

        return binding.root
    }
}
