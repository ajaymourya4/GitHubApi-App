package com.ajaymourya.githubapi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajaymourya.githubapi.network.GithubApi
import com.ajaymourya.githubapi.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RepositoryViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    lateinit var userId: String

    val repos = MutableLiveData<List<Repository>>()

    init {
        getGitHubUserRepos()
    }

    private fun getGitHubUserRepos() {
        coroutineScope.launch {
            var getRepos = GithubApi.retrofitService.getRepos(userId)
            try {
                // Await the completion of our Retrofit request
                var result = getRepos.await()
                repos.value = result
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
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
