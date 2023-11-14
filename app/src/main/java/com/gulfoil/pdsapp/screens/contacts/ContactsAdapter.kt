package com.gulfoil.pdsapp.screens.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gulfoil.pdsapp.data.entities.ContactData
import com.gulfoil.pdsapp.databinding.ItemContactBinding
import com.gulfoil.pdsapp.utils.scope

class ContactsAdapter : RecyclerView.Adapter<ContactsAdapter.VH>() {
    private val list = ArrayList<ContactData>()

    fun setData(newList: List<ContactData>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private var itemClickedListener: ((ContactData) -> Unit)? = null
    fun setItemClickedListener(f: (ContactData) -> Unit) {
        itemClickedListener = f
    }

    inner class VH(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                itemClickedListener?.invoke(list[absoluteAdapterPosition])
            }
        }

        fun bind() = binding.scope {
            val data = list[absoluteAdapterPosition]
            txtLocation.text = data.location
            txtName.text = data.name
            txtPhone.text = data.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

    override fun getItemCount(): Int = list.size
}
