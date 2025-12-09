package com.example.catgallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.catgallery.data.models.PexelsVideo

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
            val file = item.video_files.firstOrNull()
            val url = file?.link ?: return

            val uri = Uri.parse(url)
            videoView.setVideoURI(uri)

            videoView.setOnPreparedListener { mp ->
                mp.isLooping = true

//                videoView.post {
//                    val viewW = videoView.width.toFloat()
//                    val viewH = videoView.height.toFloat()
//                    val videoW = mp.videoWidth.toFloat()
//                    val videoH = mp.videoHeight.toFloat()
//                    val scaleX = viewW / videoW
//                    val scaleY = viewH / videoH
//
//                    val scale = maxOf(scaleX, scaleY)
//
//                    videoView.scaleX = scale
//                    videoView.scaleY = scale
//                }



                videoView.start()
            }
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

    override fun getItemCount(): Int = items.size
}
