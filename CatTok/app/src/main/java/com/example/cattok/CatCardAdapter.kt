package com.example.cattok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cattok.data.models.CatCard

class CatCardAdapter (private val onFavoriteClick: (CatCard) -> Unit) :
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
        private val favButton: ImageButton = itemView.findViewById(R.id.btnFavoriteCat)
        fun bind(item: CatCard) {
            factText.text = item.fact
            imageView.load(item.imageUrl) {
                crossfade(true)
            }
            favButton.setOnClickListener {
                onFavoriteClick(item)
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

