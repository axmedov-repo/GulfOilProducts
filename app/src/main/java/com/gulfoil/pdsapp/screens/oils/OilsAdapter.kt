package com.gulfoil.pdsapp.screens.oils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulfoil.pdsapp.data.remote.responses.OilResponseItem
import com.gulfoil.pdsapp.databinding.ItemOilsBinding
import com.gulfoil.pdsapp.utils.scope

class OilsAdapter : RecyclerView.Adapter<OilsAdapter.VH>() {
    private val list = ArrayList<OilResponseItem>()

    fun setData(newList: List<OilResponseItem>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((OilResponseItem) -> Unit)? = null
    fun setItemClickedListener(f: (OilResponseItem) -> Unit) {
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
