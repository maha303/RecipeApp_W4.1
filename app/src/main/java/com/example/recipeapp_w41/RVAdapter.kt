package com.example.recipeapp_w41

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(private val items: ArrayList<RecipeDetails.Datum>): RecyclerView.Adapter<RVAdapter.ItemViewHolder> () {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.apply {
           val tit = item.title
            val nam=item.author
            val g=item.ingredients
            val i =item.instructions
            tvTit.text=tit
            tvNam.text=nam
            tvG.text=g
            tvI.text=i

    }}

    override fun getItemCount()= items.size
    }
