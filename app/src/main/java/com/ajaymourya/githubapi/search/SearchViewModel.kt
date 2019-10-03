package com.ajaymourya.githubapi.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private var _navigateToResultPage = MutableLiveData<String>()

    val navigateToResultPage: LiveData<String>
        get() = _navigateToResultPage

    fun getEditText(userId : String) {
        Log.e("user"," $userId")
        _navigateToResultPage.value = userId
    }
}
