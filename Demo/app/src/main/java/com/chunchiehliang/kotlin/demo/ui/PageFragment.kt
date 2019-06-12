package com.chunchiehliang.kotlin.demo.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentPageBinding

class PageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPageBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_page, container, false
        )


        return binding.root
    }


}
