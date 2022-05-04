package com.example.slotmachinemadness

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ScoreViewModelFactory(private val playerName: String, private val winnings: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ScoreViewModel::class.java)){
            return ScoreViewModel(playerName, winnings) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}