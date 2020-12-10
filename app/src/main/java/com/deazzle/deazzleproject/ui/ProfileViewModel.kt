package com.deazzle.deazzleproject.ui

import androidx.lifecycle.*
import com.deazzle.deazzleproject.data.db.entities.ProfilesModel
import com.deazzle.deazzleproject.data.repository.ProfileRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.populateDatabase()
        }

    }

    fun getIsLoading():LiveData<Boolean>?
    {
        return repository.getIsLoading()?.asLiveData()
    }

    @ExperimentalCoroutinesApi
    fun getProfiles() : LiveData<List<ProfilesModel>> {
       return repository.getProfilesFromRepo().asLiveData()

    }

    fun getMoreProfiles() {
        viewModelScope.launch {
            repository.populateDatabase()
        }
    }

    fun markAsLiked(uuid:String)
    {
        viewModelScope.launch {
            repository.markAsLiked(uuid)
        }
    }

    fun markAsDisLiked(uuid:String)
    {
        viewModelScope.launch {
            repository.markAsDisLiked(uuid)
        }
    }


}
