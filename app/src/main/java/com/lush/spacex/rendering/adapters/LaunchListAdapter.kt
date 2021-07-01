package com.lush.spacex.rendering.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.lush.spacex.R
import com.lush.spacex.databinding.LaunchListItemCardBinding
import com.lush.spacex.rendering.displayItems.LaunchDisplayItem
import java.lang.IllegalArgumentException

private const val VIEW_TYPE_PAST_LAUNCH_LIST_DISPLAY_ITEM = 0

class LaunchListAdapter(
    private val onLaunchClicked: (LaunchDisplayItem) -> Unit
) : ListAdapter<LaunchDisplayItem, RecyclerView.ViewHolder>(Differ()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_PAST_LAUNCH_LIST_DISPLAY_ITEM -> PastLaunchListItemViewHolder(
                LaunchListItemCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onLaunchClicked
            )
            else -> throw IllegalArgumentException(
                "LaunchListAdapter does not support viewType: $viewType"
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val latestItem = getItem(position)
        @Suppress("UNCHECKED_CAST")
        (holder as? PastLaunchListItemViewHolder)?.bind(latestItem)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            getItem(position)?.also { latestItem ->
                @Suppress("UNCHECKED_CAST")
                val oldItem = (payloads as? List<LaunchDisplayItem>)?.firstOrNull()

                @Suppress("UNCHECKED_CAST")
                (holder as? PastLaunchListItemViewHolder)?.bind(latestItem, oldItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.let {
            VIEW_TYPE_PAST_LAUNCH_LIST_DISPLAY_ITEM
        } ?: throw IllegalArgumentException("$position out of bounds for data set")
    }

    private class PastLaunchListItemViewHolder(
        private val binding: LaunchListItemCardBinding,
        private val onItemClicked: (LaunchDisplayItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            latestItem: LaunchDisplayItem,
            oldItem: LaunchDisplayItem? = null
        ) {
            oldItem?.also { old ->
                if (latestItem.id != old.id)
                    bindItemClick(latestItem)
                if (latestItem.name != old.name)
                    bindName(latestItem)
                if (latestItem.launchDate != old.launchDate)
                    bindLaunchDate(latestItem)
                if (latestItem.success != old.success)
                    bindSuccess(latestItem)
                if (latestItem.imageUrl != old.imageUrl)
                    bindImage(latestItem)
            } ?: run {
                bindItemClick(latestItem)
                bindName(latestItem)
                bindLaunchDate(latestItem)
                bindSuccess(latestItem)
                bindImage(latestItem)
            }
        }

        private fun bindItemClick(
            latestItem: LaunchDisplayItem
        ) {
            binding.root.setOnClickListener {
                onItemClicked(latestItem)
            }
        }

        private fun bindName(
            latestItem: LaunchDisplayItem
        ) {
            binding.launchName.text = latestItem.name
        }

        private fun bindLaunchDate(
            latestItem: LaunchDisplayItem
        ) {
            binding.launchDate.text = latestItem.launchDate.toString()
        }

        private fun bindSuccess(
            latestItem: LaunchDisplayItem
        ) {
            binding.success.text = when (latestItem.success) {
                true -> binding.root.context.getString(R.string.successful_launch)
                else -> binding.root.context.getString(R.string.failed_launch)
            }
        }

        private fun bindImage(
            latestItem: LaunchDisplayItem
        ) {
            Glide.with(binding.root.context)
                .asDrawable()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(latestItem.imageUrl)
                .transform(
                    CenterCrop(),
                    RoundedCorners(
                        binding.root.context.resources.getDimension(
                            R.dimen.image_corner_radius
                        ).toInt()
                    )
                )
                .placeholder(R.drawable.shape_rounded_rectangle)
                .into(binding.launchImage)
        }
    }

    private class Differ : DiffUtil.ItemCallback<LaunchDisplayItem>() {
        override fun areItemsTheSame(
            oldItem: LaunchDisplayItem,
            newItem: LaunchDisplayItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LaunchDisplayItem,
            newItem: LaunchDisplayItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(
            oldItem: LaunchDisplayItem,
            newItem: LaunchDisplayItem
        ): Any {
            return oldItem
        }
    }
}