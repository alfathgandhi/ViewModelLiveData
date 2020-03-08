package com.example.belajarassesment1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class HasilViewModel(score:Int): ViewModel() {
private val xscore = MutableLiveData<Int>()
    var score:LiveData<Int> = xscore
    init {
        Log.i("HasilViewModel","Score : $score")
        xscore.value = score

    }
}