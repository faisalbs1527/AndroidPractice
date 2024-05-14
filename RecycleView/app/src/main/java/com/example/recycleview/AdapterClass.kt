package com.example.recycleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterClass(private val dataList: ArrayList<DataClass>) :
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
        holder.rvImage.setImageResource(ItemsViewModel.dataImage)
        holder.rvTitle.text = ItemsViewModel.dataTitle
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class viewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.imageView)
        val rvTitle: TextView = itemView.findViewById(R.id.textView)
    }
}