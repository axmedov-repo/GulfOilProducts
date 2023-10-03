package com.axmedov.gulfapp.screens.old_variant.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.data.entities.ProductData
import com.axmedov.gulfapp.databinding.ItemProductBinding
import com.axmedov.gulfapp.utils.scope

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.VH>() {
    private val list = ArrayList<ProductData>()

    fun setData(newList: List<ProductData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((ProductData) -> Unit)? = null
    fun setItemClickedListener(f: (ProductData) -> Unit) {
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
            imgProduct.setImageResource(data.image)
            txtNameProduct.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
