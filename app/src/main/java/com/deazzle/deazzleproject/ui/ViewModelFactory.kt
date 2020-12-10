package com.deazzle.deazzleproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.deazzle.deazzleproject.data.repository.ProfileRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: ProfileRepository
    ) : ViewModelProvider.Factory    {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}