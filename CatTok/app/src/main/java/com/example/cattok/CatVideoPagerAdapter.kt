package com.example.cattok

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.cattok.data.models.PexelsVideo

class CatVideoPagerAdapter :
    RecyclerView.Adapter<CatVideoPagerAdapter.VideoPageViewHolder>() {

    private val items = mutableListOf<PexelsVideo>()

    fun submitList(newItems: List<PexelsVideo>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class VideoPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val videoView: VideoView = itemView.findViewById(R.id.videoView)

        fun bind(item: PexelsVideo) {
            videoView.stopPlayback()

            val file = item.video_files.firstOrNull()
            if (file == null) return

            try {
                videoView.setVideoURI(Uri.parse(file.link))

                videoView.setOnPreparedListener { mp ->
                    mp.isLooping = true
                    videoView.start()
                }

                videoView.setOnErrorListener { _, _, _ ->
                    videoView.stopPlayback()
                    true
                }

            } catch (e: Exception) {
                videoView.stopPlayback()
            }
        }


        fun stop() {
            videoView.stopPlayback()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoPageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video_page, parent, false)
        return VideoPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onViewRecycled(holder: VideoPageViewHolder) {
        super.onViewRecycled(holder)
        holder.stop()
    }


    override fun getItemCount(): Int = items.size
}
