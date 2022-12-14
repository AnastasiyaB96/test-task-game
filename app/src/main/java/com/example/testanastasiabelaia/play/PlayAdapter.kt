package com.example.testanastasiabelaia.play

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.testanastasiabelaia.R

class PlayAdapter(private val listImage: List<Int>) : RecyclerView.Adapter<PlayAdapter.PlayViewHolder>() {
    class PlayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image: ImageView = view.findViewById(R.id.item_background)

        fun bind(idImage: Int) {
            image.background = ResourcesCompat.getDrawable(image.resources, idImage, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.element_item, parent, false)
        return PlayViewHolder(layout)
    }

    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        holder.bind(listImage[position % listImage.size])
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}