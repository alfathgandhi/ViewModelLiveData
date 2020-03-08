package com.example.belajarassesment1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.belajarassesment1.databinding.FragmentHasilBinding

/**
 * A simple [Fragment] subclass.
 */
class Hasil : Fragment() {
    private lateinit var viewModel: HasilViewModel
    private lateinit var  factory: HasilViewModelFactory
private lateinit var bind:FragmentHasilBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind=DataBindingUtil.inflate(inflater,R.layout.fragment_hasil, container, false)
        val argument by navArgs<HasilArgs>()

        factory= HasilViewModelFactory(argument.score)

    viewModel=ViewModelProviders.of(this,factory).get(HasilViewModel::class.java)


        viewModel.score.observe(viewLifecycleOwner, Observer { scoreAkhir->

            bind.textView2.text="SCORE: "+scoreAkhir
        })


        bind.button.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_hasil_to_titleFragment3)
        }

        return bind.root

    }

}
