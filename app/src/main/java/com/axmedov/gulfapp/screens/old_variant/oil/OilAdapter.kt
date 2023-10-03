package com.axmedov.gulfapp.screens.old_variant.oil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.data.entities.OilData
import com.axmedov.gulfapp.databinding.ItemOilBinding
import com.axmedov.gulfapp.utils.scope

class OilAdapter : RecyclerView.Adapter<OilAdapter.VH>() {
    private val list = ArrayList<OilData>()

    fun setData(newList: List<OilData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((OilData) -> Unit)? = null
    fun setItemClickedListener(f: (OilData) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemOilBinding) : RecyclerView.ViewHolder(binding.root) {
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
        VH(ItemOilBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
