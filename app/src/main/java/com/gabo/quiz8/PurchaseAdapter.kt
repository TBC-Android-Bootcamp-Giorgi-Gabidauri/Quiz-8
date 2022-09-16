package com.gabo.quiz8

import com.gabo.quiz8.databinding.PurchaseItemBinding
import com.gabo.quiz8.domain.models.PurchaseItemModel

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabo.quiz8.common.extension.loadImage

class PurchaseAdapter(private val click: (PurchaseItemModel) -> Unit) :
    RecyclerView.Adapter<PurchaseAdapter.PurchaseVH>() {
    private var list: List<PurchaseItemModel> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<PurchaseItemModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class PurchaseVH(private val binding: PurchaseItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PurchaseItemModel, click: (PurchaseItemModel) -> Unit) {
            with(binding){
                ivPhoto.loadImage(model.cover)
                tvPrice.text = model.price
                tvTitle.text = model.title
                itemView.setOnClickListener { click(model) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseVH {
        val binding =
            PurchaseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchaseVH(binding)
    }

    override fun onBindViewHolder(holder: PurchaseVH, position: Int) {
        val item = list[position]
        holder.bind(item, click)
    }

    override fun getItemCount(): Int = list.size
}