package com.ajaymourya.githubapi.repository

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ajaymourya.githubapi.network.Repository

/**
 * Created by Ajay Mourya on 02,October,2019
 */

@BindingAdapter("repositoryName")
fun TextView.setRepositoryName(item: Repository) {
    text = item.name
}
