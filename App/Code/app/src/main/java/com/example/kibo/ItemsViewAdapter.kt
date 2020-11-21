package com.example.kibo

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import kotlinx.android.synthetic.main.fragment_items.view.*

class ItemsViewAdapter(private val values: List<ItemClass>) : RecyclerView.Adapter<ItemsViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        if(item.id == 0) holder.imageView.setImageResource(R.drawable.i_hat)
        if(item.id == 1) holder.imageView.setImageResource(R.drawable.i_morellion_black)
        if(item.id == 2) holder.imageView.setImageResource(R.drawable.i_destello_black)
        if(item.id == 3) holder.imageView.setImageResource(R.drawable.i_rock_black)
        if(item.id == 4) holder.imageView.setImageResource(R.drawable.i_abby_black)
        if(item.id == 5) holder.imageView.setImageResource(R.drawable.i_tangana_black)
        if(item.id == 6) holder.imageView.setImageResource(R.drawable.i_rat_black)
        if(item.id == 7) holder.imageView.setImageResource(R.drawable.i_coat_black)
        if(item.id == 8) holder.imageView.setImageResource(R.drawable.i_hysteria_black)

        holder.idView.text = item.name.toUpperCase()
        holder.contentView.text = item.property
        holder.descriptionView.text = item.description
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView : ImageView = view.imageItem
        val idView: TextView = view.item_number // ITEM NAME
        val contentView: TextView = view.content // PROPERTY
        val descriptionView: TextView = view.itemDescriptionText

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}