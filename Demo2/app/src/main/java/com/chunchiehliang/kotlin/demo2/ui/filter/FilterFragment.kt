package com.chunchiehliang.kotlin.demo2.ui.filter


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chunchiehliang.kotlin.demo2.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentFilterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentFilterBinding.inflate(inflater)

        return binding.root
    }

    companion object {
        fun getInstance(): FilterFragment {
            return FilterFragment()
        }
    }
}
