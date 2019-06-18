package com.chunchiehliang.kotlin.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(val recipeList: MutableList<RecipeViewModel.Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.ListItemViewHolder>() {

    override fun getItemCount(): Int = recipeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item_recipe, parent, false)


        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }



    class ListItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Any) {
            binding.setVariable(BR.recipeViewModel, recipe)
            binding.executePendingBindings()
        }
    }
}