package com.example.catgallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catgallery.models.CatCard

class CatCardAdapter :
    ListAdapter<CatCard, CatCardAdapter.CatViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<CatCard>() {
        override fun areItemsTheSame(oldItem: CatCard, newItem: CatCard): Boolean {
            return oldItem.imageUrl == newItem.imageUrl && oldItem.fact == newItem.fact
        }

        override fun areContentsTheSame(oldItem: CatCard, newItem: CatCard): Boolean {
            return oldItem == newItem
        }
    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.catImage)
        private val factText: TextView = itemView.findViewById(R.id.catFact)

        fun bind(item: CatCard) {
            factText.text = item.fact
            imageView.load(item.imageUrl) {
                crossfade(true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
