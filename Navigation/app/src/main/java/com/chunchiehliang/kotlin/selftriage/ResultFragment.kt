package com.chunchiehliang.kotlin.selftriage


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunchiehliang.kotlin.selftriage.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(
            inflater, R.layout.fragment_result, container, false
        )

        binding.buttonTriageNotify.setOnClickListener {
            Toast.makeText(context, "You have been prioritized!", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }


}
