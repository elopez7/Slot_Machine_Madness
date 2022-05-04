package com.example.slotmachinemadness

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class ScoreViewModel(private val userName: String, private val winnings: Int) : ViewModel() {

    private val _playerName = MutableLiveData<String>()
    val playerName : LiveData<String> get() = _playerName

    private val _finalWinnings = MutableLiveData<Int>()
    val finalWinnings: LiveData<Int> get() = _finalWinnings

    init {
        _playerName.value = userName
        _finalWinnings.value = winnings
    }

    fun gamble(){
        val numberList = listOf(0, 2)
        val randomIndex = Random.nextInt(numberList.size)
        _finalWinnings.value = _finalWinnings.value?.times(numberList[randomIndex])
    }
}