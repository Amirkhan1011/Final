package com.example.catgallery.data.models

data class CatVid(
    val page: Int,
    val per_page: Int,
    val videos: List<PexelsVideo>
)

data class PexelsVideo(
    val id: Long,
    val image: String,
    val video_files: List<PexelsVideoFile>
)

data class PexelsVideoFile(
    val id: Long,
    val link: String,
    val quality: String?,
    val width: Int?,
    val height: Int?
)
