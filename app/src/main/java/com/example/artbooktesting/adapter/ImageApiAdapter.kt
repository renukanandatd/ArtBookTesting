package com.example.artbooktesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.R
import javax.inject.Inject

class ImageApiAdapter@Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ImageApiAdapter.ImageViewHolder>() {

    private var onItemClickListener : ((String) -> Unit)? = null
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)
    var images: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageApiAdapter.ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_row, parent, false)
        return ImageApiAdapter.ImageViewHolder(view)
    }

    fun setOnClickListener(listener : (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageApiAdapter.ImageViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.singleArtImageView)
        val url = images[position]
        holder.itemView.apply {
            glide.load(url).into(imageView)
            setOnClickListener {
                onItemClickListener?.let {
                    it(url)
                }
            }
        }
    }
}