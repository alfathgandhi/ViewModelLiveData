package com.example.belajarassesment1

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

class GameViewmodel : ViewModel(){


    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }

    companion object{
        private const val DONE = 0L
        private const val  ONE_SEC= 1000L
        private const val  CD= 6000L


    }


    var word=MutableLiveData<String>()
    lateinit var wordlist:MutableList<String>

    private  val xscore=MutableLiveData<Int>()
    var score:LiveData<Int> = xscore

    private val xgameover=MutableLiveData<Boolean>()
    var gameover:LiveData<Boolean> = xgameover

    private val timer:CountDownTimer

    private  var xcurrentTime=MutableLiveData<Long>()
    val currenttime: LiveData<Long> = xcurrentTime

    val stringcurrenttime=Transformations.map(currenttime,{
        time-> DateUtils.formatElapsedTime(time)
    })

    private var xbuzz=MutableLiveData<BuzzType>()
    val buzz:LiveData<BuzzType> = xbuzz





    init {
        xbuzz.value=BuzzType.NO_BUZZ

        Log.i("GameViewmodel","GameViewmodel created!")
        shuffleWordlist()
        getWord()
        xscore.value=0
        xgameover.value=false


        timer= object : CountDownTimer(CD, ONE_SEC){
            override fun onTick(millisUntilFinished: Long) {
               xcurrentTime.value=millisUntilFinished/ ONE_SEC
            }

            override fun onFinish() {
                finished()

            }
        }
        timer.start()




    }
    fun onBuzzComplete() {
        xbuzz.value = BuzzType.NO_BUZZ
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewmodel","Cleared")
        timer.cancel()

    }

    fun shuffleWordlist() {
        wordlist = mutableListOf(
            "Alfath",
            "Gandhi",
            "Iskandar",
            "Haha"
        )

        wordlist.shuffle()
    }
        fun getWord(){

            word.value=wordlist.removeAt(0)

        }
        fun tambahScore(){
          xbuzz.value=BuzzType.CORRECT
            xscore.value=xscore.value?.plus(1)
           varCheck()


        }
    fun kurangScore(){
        if(xscore.value!=0){xscore.value=xscore.value?.minus(1)
     varCheck()
        }


    }

    fun varCheck() {


        if (wordlist.isEmpty()) {
            shuffleWordlist()

        }
            getWord()

        }

fun finished(){
    xcurrentTime.value= DONE
    xgameover.value=true
}


    }
