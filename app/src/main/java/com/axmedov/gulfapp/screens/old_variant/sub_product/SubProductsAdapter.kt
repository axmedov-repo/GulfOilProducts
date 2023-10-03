package com.axmedov.gulfapp.screens.old_variant.sub_product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.data.entities.SubProductData
import com.axmedov.gulfapp.databinding.ItemSubProductBinding
import com.axmedov.gulfapp.utils.scope

class SubProductsAdapter : RecyclerView.Adapter<SubProductsAdapter.VH>() {
    private val list = ArrayList<SubProductData>()

    fun setData(newList: List<SubProductData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((SubProductData) -> Unit)? = null
    fun setItemClickedListener(f: (SubProductData) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemSubProductBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickedListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() = binding.scope {
            val data = list[absoluteAdapterPosition]
            txtName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemSubProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
