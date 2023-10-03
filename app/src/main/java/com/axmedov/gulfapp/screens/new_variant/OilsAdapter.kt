package com.axmedov.gulfapp.screens.new_variant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.data.entities.NewOilData
import com.axmedov.gulfapp.databinding.ItemOilsBinding
import com.axmedov.gulfapp.utils.scope

class OilsAdapter : RecyclerView.Adapter<OilsAdapter.VH>() {
    private val list = ArrayList<NewOilData>()

    fun setData(newList: List<NewOilData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((NewOilData) -> Unit)? = null
    fun setItemClickedListener(f: (NewOilData) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemOilsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickedListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() = binding.scope {
            val data = list[absoluteAdapterPosition]
//            imgProduct.setImageResource(data.image)
            txtNameProduct.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemOilsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
