package com.example.adesh.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adesh.data.model.WebSiteModel
import com.example.adesh.databinding.ItemWebsiteBinding

class WebSiteAdapter(
    private val websites: List<WebSiteModel>,
    private val itemClick: (website: WebSiteModel) -> Unit,
    private val onCardClick: (website: WebSiteModel) -> Unit
) :
    RecyclerView.Adapter<WebSiteAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemWebsiteBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWebsiteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.website = websites[position]
        holder.binding.tvWebsiteUrl.setOnClickListener {
            itemClick.invoke(websites[position])
        }
        holder.binding.ivEdit.setOnClickListener {
            onCardClick.invoke(websites[position])
        }
    }

    override fun getItemCount(): Int {
        return websites.size
    }
}