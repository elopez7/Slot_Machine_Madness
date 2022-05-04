package com.example.slotmachinemadness

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel(var playerName: String): ViewModel() {

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _totalMoney = MutableLiveData<Int>()
    val totalMoney: LiveData<Int> get() = _totalMoney

    private val _totalWinnings = MutableLiveData<Int>()
    val totalWinnings : LiveData<Int> get() = _totalWinnings

    private val _spinsLeft = MutableLiveData<Int>()
    val spinsLeft : LiveData<Int> = _spinsLeft

    init {
        _userName.value = playerName
        _totalMoney.value = 1000
        _totalWinnings.value = 0
        _spinsLeft.value = 0
    }

    fun increaseMoneyAndWinnings(increaseAmount: Int) {
        _totalMoney.value = _totalMoney.value?.plus(increaseAmount)
        _totalWinnings.value = totalWinnings.value?.plus(increaseAmount)
    }

    fun increaseSpins() {
        _spinsLeft.value = _spinsLeft.value?.plus(1)
        _totalMoney.value = _totalMoney.value?.minus(100)
    }

    fun decreaseSpins() {
        _spinsLeft.value = _spinsLeft.value?.minus(1)
    }

    fun compareResults(result1: Int, result2: Int, result3: Int) {
        if(result1 == R.drawable.ic_dollar_sign
            || result2 == R.drawable.ic_dollar_sign
            || result3 == R.drawable.ic_dollar_sign) {
            increaseMoneyAndWinnings(1000)
            return
        }
        else if(result1 == R.drawable.ic_crossed_dollar_sign
            || result2 == R.drawable.ic_crossed_dollar_sign
            || result3 == R.drawable.ic_crossed_dollar_sign) {
            _spinsLeft.value = 0
            return
        }
        else if(result1 == R.drawable.ic_diamond
            || result2 == R.drawable.ic_diamond
            || result3 == R.drawable.ic_diamond) {
            increaseMoneyAndWinnings(100)
            return
        }
        else if(result1 == R.drawable.ic_dollar_sign_round
            || result2 == R.drawable.ic_dollar_sign_round
            || result3 == R.drawable.ic_dollar_sign_round) {
            increaseMoneyAndWinnings(500)
            return
        }
        else if(result1 == R.drawable.ic_android_head
            && result2 == R.drawable.ic_android_head
            && result3 == R.drawable.ic_android_head) {
            increaseMoneyAndWinnings(200)
            return
        }
        else if(result1 == R.drawable.ic_bug
            && result2 == R.drawable.ic_bug
            && result3 == R.drawable.ic_bug) {
            increaseMoneyAndWinnings(100)
            return
        }
        else if(result1 == R.drawable.ic_hundred_dollar_bill
            && result2 == R.drawable.ic_hundred_dollar_bill
            && result3 == R.drawable.ic_hundred_dollar_bill) {
            increaseMoneyAndWinnings(300)
            return
        }
        else if(result1 == R.drawable.ic_musical_note
            && result2 == R.drawable.ic_musical_note
            && result3 == R.drawable.ic_musical_note) {
            increaseMoneyAndWinnings(400)
            return
        }
    }
}