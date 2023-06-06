package com.example.smiletryone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val repository: DataStoreRepository
): ViewModel() {
    fun saveOnBoardingState(complete: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveOnboardingState(completed = complete)
        }
    }
}