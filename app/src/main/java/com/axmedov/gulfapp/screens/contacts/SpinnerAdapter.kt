package com.axmedov.gulfapp.screens.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.databinding.ItemSpinnerBinding
import com.axmedov.gulfapp.utils.scope

class SpinnerAdapter : RecyclerView.Adapter<SpinnerAdapter.VH>() {
    private val list = ArrayList<String>()


    fun setData(newList: List<String>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((String) -> Unit)? = null
    fun setItemClickedListener(f: (String) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemSpinnerBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickedListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() = binding.scope {
            val data = list[absoluteAdapterPosition]
//            imgProduct.setImageResource(data.image)
            txtItem.text = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemSpinnerBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
