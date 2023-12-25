package com.andricohalim.freegames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.andricohalim.freegames.repository.GamesRepository
import com.andricohalim.freegames.response.GamesResponse
import com.andricohalim.freegames.util.Result
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: GamesRepository) : ViewModel() {

    private val _listStory = MutableLiveData<Result<List<GamesResponse>>>()
    val listStory: LiveData<Result<List<GamesResponse>>> = _listStory

    init {
        getListStory()
    }

    fun getListStory() {
        viewModelScope.launch {
            val response = userRepository.getGames()
            response.asFlow().collect {
                _listStory.value = it
            }
        }
    }
}