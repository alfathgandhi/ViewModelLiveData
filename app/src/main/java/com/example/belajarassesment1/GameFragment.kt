package com.example.belajarassesment1

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.belajarassesment1.databinding.FragmentGameBinding
import kotlinx.coroutines.delay
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    private lateinit var viewmodel: GameViewmodel
    private var score=0



    @SuppressLint("SetTextI18n")
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)


        viewmodel = ViewModelProviders.of(this).get(GameViewmodel::class.java)

        viewmodel.buzz.observe(viewLifecycleOwner, Observer {
            buzzer->
            if (buzzer != GameViewmodel.BuzzType.NO_BUZZ) {
                buzz(buzzer.pattern)
                viewmodel.onBuzzComplete()
            }



        })

        binding.gameXML=viewmodel
        binding.setLifecycleOwner(this)

//        viewmodel.score.observe(viewLifecycleOwner, Observer {
//                newScore -> binding.idscore.text =
//            getString(R.string.score) + newScore.toString()
//        })
//        viewmodel.word.observe(viewLifecycleOwner, Observer {
//
//                newWord->binding.idword.text = newWord
//        })
        viewmodel.gameover.observe(viewLifecycleOwner, Observer { gameFinished->
            if(gameFinished==true){


                pindahFragment()

            }
        })
//        viewmodel.currenttime.observe(viewLifecycleOwner, Observer { current->
//            binding.current.text="Sisa Waktu:"+ DateUtils.formatElapsedTime(current)
//        })

        binding.apply {
//            binding.Correct.setOnClickListener {
//                hitung(cek = true)
//                viewmodel.varCheck()
//
//}
//
//            binding.SKIP.setOnClickListener {
//                hitung(cek = false)
//                viewmodel.varCheck()
//
//            }
        }
        return binding.root

    }





    fun setWord() {
// sudah jadi live data

    }

    @SuppressLint("SetTextI18n")
    fun setScore() {

    //sudah jadi live data

    }
    fun hitung(cek:Boolean){
        if(cek==true){
            viewmodel.tambahScore()
        }else{
            viewmodel.kurangScore()
        }

    }

    fun pindahFragment(){
        val nav = GameFragmentDirections.actionGameFragmentToHasil(viewmodel.score.value?:0)
        view?.findNavController()?.navigate(nav)

    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }





}






