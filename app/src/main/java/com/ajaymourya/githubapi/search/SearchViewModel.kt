package com.ajaymourya.githubapi.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SearchViewModel : ViewModel() {

    private var _navigateToResultPage = MutableLiveData<String>()

    val navigateToResultPage: LiveData<String>
        get() = _navigateToResultPage

    var isOnline = MutableLiveData<Boolean>()

   fun noInternetConnection(){
       isOnline.value = false
   }

    fun getEditText(userId: String) {
            _navigateToResultPage.value = userId
    }
}
