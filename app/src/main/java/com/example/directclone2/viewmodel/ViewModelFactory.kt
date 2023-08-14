package com.example.directclone2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.directclone2.model.ProfileRepository

class ViewModelFactory(private val repo : ProfileRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SettingViewModel::class.java)){
            return SettingViewModel(repo = repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}