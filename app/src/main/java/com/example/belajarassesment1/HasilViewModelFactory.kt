package com.example.belajarassesment1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HasilViewModelFactory(private val score:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HasilViewModel::class.java)){
            return HasilViewModel(score) as T
        }
        throw IllegalArgumentException ("Akses Illegal")
    }
}