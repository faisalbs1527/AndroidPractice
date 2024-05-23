package com.example.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.recycleview.model.Data
import com.example.recycleview.model.ProductClass

class AdapterClass(private val dataList: List<Data>) :
    RecyclerView.Adapter<AdapterClass.viewHolderClass>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterClass.viewHolderClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return viewHolderClass(view)
    }

    override fun onBindViewHolder(holder: AdapterClass.viewHolderClass, position: Int) {
        val ItemsViewModel = dataList[position]
        holder.title.text = ItemsViewModel.ProductPrice.Price
        holder.id.text = ItemsViewModel.Name

        Glide.with(holder.image.context)
            .load(ItemsViewModel.PictureModels[0].ImageUrl)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class viewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val id: TextView = itemView.findViewById(R.id.id)
        val image : ImageView = itemView.findViewById(R.id.imageView)
    }
}