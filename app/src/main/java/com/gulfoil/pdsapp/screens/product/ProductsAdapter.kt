package com.gulfoil.pdsapp.screens.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gulfoil.pdsapp.data.remote.responses.ProductResponseItem
import com.gulfoil.pdsapp.databinding.ItemProductBinding
import com.gulfoil.pdsapp.utils.scope

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.VH>() {
    private val list = ArrayList<ProductResponseItem>()

    fun setData(newList: List<ProductResponseItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((ProductResponseItem) -> Unit)? = null
    fun setItemClickedListener(f: (ProductResponseItem) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickedListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() = binding.scope {
            val data = list[absoluteAdapterPosition]
            Glide.with(imgProduct.context).load(data.image).into(imgProduct)
            txtNameProduct.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
