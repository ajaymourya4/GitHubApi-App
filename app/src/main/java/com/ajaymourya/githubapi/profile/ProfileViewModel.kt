package com.ajaymourya.githubapi.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajaymourya.githubapi.network.GithubApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _summary = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val summary: LiveData<String>
        get() = _summary

    private val _profileImgUrl = MutableLiveData<String>()

    val profileImgUrl: LiveData<String>
        get() = _profileImgUrl

    private val _repositoryCount = MutableLiveData<String>()

    val repositoryCount: LiveData<String>
        get() = _repositoryCount

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    lateinit var userId: String

    init {
        getGitHubUserInfo()
    }

    private fun getGitHubUserInfo() {
        coroutineScope.launch {
            var getFeed = GithubApi.retrofitService.getFeed(userId)
            try {
                // Await the completion of our Retrofit request
                var result = getFeed.await()
                _profileImgUrl.value = result.profileImgUrl
                _summary.value = "Summary: ${result.summary} "
                _repositoryCount.value = "No of Repository: ${result.noOfRepository}"
            } catch (e: Exception) {
                _summary.value = "Failure: ${e.message}"
            }
        }
    }

    //When the [ViewModel] is finished, we cancel our coroutine [viewModelJob],
    // which tells the Retrofit service to stop.

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
