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
import com.lush.spacex.databinding.RocketListItemCardBinding
import com.lush.spacex.rendering.displayItems.RocketDisplayItem
import java.lang.IllegalArgumentException

private const val VIEW_TYPE_ROCKET_LIST_DISPLAY_ITEM = 0

class RocketListAdapter(
    private val onRocketClicked: (RocketDisplayItem) -> Unit
) : ListAdapter<RocketDisplayItem, RecyclerView.ViewHolder>(Differ()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ROCKET_LIST_DISPLAY_ITEM -> RocketListItemViewHolder(
                RocketListItemCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onRocketClicked
            )
            else -> throw IllegalArgumentException(
                "RocketListAdapter does not support viewType: $viewType"
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val latestItem = getItem(position)
        @Suppress("UNCHECKED_CAST")
        (holder as? RocketListItemViewHolder)?.bind(latestItem)
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
                val oldItem = (payloads as? List<RocketDisplayItem>)?.firstOrNull()

                @Suppress("UNCHECKED_CAST")
                (holder as? RocketListItemViewHolder)?.bind(latestItem, oldItem)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.let {
            VIEW_TYPE_ROCKET_LIST_DISPLAY_ITEM
        } ?: throw IllegalArgumentException("$position out of bounds for data set")
    }

    private class RocketListItemViewHolder(
        private val binding: RocketListItemCardBinding,
        private val onItemClicked: (RocketDisplayItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            latestItem: RocketDisplayItem,
            oldItem: RocketDisplayItem? = null
        ) {
            oldItem?.also { old ->
                if (latestItem.id != old.id)
                    bindItemClick(latestItem)
                if (latestItem.name != old.name)
                    bindName(latestItem)
                if (latestItem.launchDate != old.launchDate)
                    bindLaunchDate(latestItem)
                if (latestItem.successRatePercentage != old.successRatePercentage)
                    bindSuccessRatePercentage(latestItem)
                if (latestItem.imageUrl != old.imageUrl)
                    bindImage(latestItem)
            } ?: run {
                bindItemClick(latestItem)
                bindName(latestItem)
                bindLaunchDate(latestItem)
                bindSuccessRatePercentage(latestItem)
                bindImage(latestItem)
            }
        }

        private fun bindItemClick(
            latestItem: RocketDisplayItem
        ) {
            binding.root.setOnClickListener {
                onItemClicked(latestItem)
            }
        }

        private fun bindName(
            latestItem: RocketDisplayItem
        ) {
            binding.rocketName.text = latestItem.name
        }

        private fun bindLaunchDate(
            latestItem: RocketDisplayItem
        ) {
            binding.launchDate.text = latestItem.launchDate.toString()
        }

        private fun bindSuccessRatePercentage(
            latestItem: RocketDisplayItem
        ) {
            binding.successRate.text = latestItem.successRatePercentage.toString()
        }

        private fun bindImage(
            latestItem: RocketDisplayItem
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
                .into(binding.rocketImage)
        }
    }

    private class Differ : DiffUtil.ItemCallback<RocketDisplayItem>() {
        override fun areItemsTheSame(
            oldItem: RocketDisplayItem,
            newItem: RocketDisplayItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RocketDisplayItem,
            newItem: RocketDisplayItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(
            oldItem: RocketDisplayItem,
            newItem: RocketDisplayItem
        ): Any {
            return oldItem
        }
    }
}