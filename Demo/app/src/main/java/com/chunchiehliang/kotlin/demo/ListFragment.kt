package com.chunchiehliang.kotlin.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.chunchiehliang.kotlin.demo.databinding.FragmentListBinding
import com.google.android.material.snackbar.Snackbar


class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.card.setOnClickListener {
            findNavController().navigate(R.id.action_list_to_detail)
        }
        binding.fabNew.setOnClickListener {
            Snackbar.make(binding.root, "Next", Snackbar.LENGTH_SHORT).show()
        }
        return binding.root
    }
}
