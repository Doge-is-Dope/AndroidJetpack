package com.chunchiehliang.kotlin.demo.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentDetailBinding



class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail, container, false)
        return binding.root
    }


}
