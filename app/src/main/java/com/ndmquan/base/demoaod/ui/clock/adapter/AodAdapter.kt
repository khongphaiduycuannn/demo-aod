package com.ndmquan.base.demoaod.ui.clock.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.ndmquan.base.demoaod.databinding.ItemHomeAodBinding
import com.ndmquan.base.demoaod.ui.clock.data.AodTheme

class AodAdapter(
    private val onClick: (AodTheme) -> Unit
) : Adapter<AodAdapter.AodViewHolder>() {

    inner class AodViewHolder(
        private val binding: ItemHomeAodBinding
    ) : ViewHolder(binding.root) {

        fun bind(aod: AodTheme) {
            Glide.with(binding.root).load(aod.thumbnail).into(binding.imageView)

            binding.root.setOnClickListener {
                onClick(aod)
            }
        }
    }


    private val themes = mutableListOf<AodTheme>()

    fun setData(themes: List<AodTheme>) {
        this.themes.clear()
        this.themes.addAll(themes)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AodViewHolder {
        val binding = ItemHomeAodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AodViewHolder(binding)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: AodViewHolder, position: Int) {
        holder.bind(themes[position])
    }
}