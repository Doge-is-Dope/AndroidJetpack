package com.chunchiehliang.kotlin.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(val wordList: MutableList<WordViewModel.Word>) :
    RecyclerView.Adapter<WordAdapter.ListItemViewHolder>() {

    override fun getItemCount(): Int = wordList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_word, parent, false)


        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(wordList[position])
    }

    class ListItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Any) {
            binding.setVariable(BR.wordViewModel, word)
            binding.executePendingBindings()
        }
    }
}