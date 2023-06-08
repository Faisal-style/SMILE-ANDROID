package com.example.smiletryone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smiletryone.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    fun saveUserToken(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveUserToken(token = token)
        }
    }
}