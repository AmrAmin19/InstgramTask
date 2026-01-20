package com.example.instgramtask.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instgramtask.Model.Media
import com.example.instgramtask.Model.MediaType
import com.example.instgramtask.Model.Post
import com.example.instgramtask.R
import com.example.instgramtask.databinding.ItemPostBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem


class PostDiff : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}


class FeedAdapter :
    ListAdapter<Post, RecyclerView.ViewHolder>(PostDiff()) {



    companion object {
        private const val TYPE_IMAGE = 0
        private const val TYPE_VIDEO = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).media.first().type) {
            MediaType.IMAGE -> TYPE_IMAGE
            MediaType.VIDEO -> TYPE_VIDEO
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(inflater, parent, false)

        return when (viewType) {
            TYPE_IMAGE -> ImageViewHolder(binding)
            else -> VideoViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItem(position)

        when (holder) {
            is ImageViewHolder -> holder.bind(post)
            is VideoViewHolder -> holder.bind(post)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is VideoViewHolder) {
            holder.releasePlayer()
        }
        super.onViewRecycled(holder)
    }



    class ImageViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            val image = post.media.first()

            binding.tvUsername.text = post.username
            binding.tvCaption.text = post.caption

            binding.imgPost.visibility = View.VISIBLE
            binding.videoPost.visibility = View.GONE

           // binding.imgPost.setImageResource(R.drawable.i1)


            Glide.with(binding.root)
                .load(image.uri)
                .into(binding.imgPost)
        }

    }



    class VideoViewHolder(
        private val binding: ItemPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var player: ExoPlayer? = null

        fun bind(post: Post) {
            val video = post.media.first()

            binding.tvUsername.text = post.username
            binding.tvCaption.text = post.caption

            binding.imgPost.visibility = View.GONE
            binding.videoPost.visibility = View.VISIBLE

            player = ExoPlayer.Builder(binding.root.context).build().also {
                binding.videoPost.player = it
                it.setMediaItem(MediaItem.fromUri(video.uri))
                it.prepare()
                it.playWhenReady = false
            }
        }


        fun play() {
            player?.playWhenReady = true
        }

        fun pause() {
            player?.playWhenReady = false
        }



        fun releasePlayer() {
            player?.release()
            player = null
            binding.videoPost.player = null
        }
    }



}


