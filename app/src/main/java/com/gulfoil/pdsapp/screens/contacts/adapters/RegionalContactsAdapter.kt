package com.gulfoil.pdsapp.screens.contacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gulfoil.pdsapp.data.remote.responses.product.RegionalContactResponseItem
import com.gulfoil.pdsapp.databinding.ItemRegionalContactBinding
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.visible

class RegionalContactsAdapter :
    ListAdapter<RegionalContactResponseItem, RegionalContactsAdapter.ContactViewHolder>(RegionalContactItemDiffCallback()) {

    private var phoneClickedListener: ((String) -> Unit)? = null
    fun setPhoneClickedListener(f: (String) -> Unit) {
        phoneClickedListener = f
    }

    private var emailClickedListener: ((String) -> Unit)? = null
    fun setEmailClickedListener(f: (String) -> Unit) {
        emailClickedListener = f
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemRegionalContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ItemRegionalContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.scope {
                txtPhone.setOnClickListener {
                    if (!getItem(absoluteAdapterPosition).phone.isNullOrEmpty()) {
                        phoneClickedListener?.invoke(getItem(absoluteAdapterPosition).phone.toString())
                    }
                }
                txtEmail.setOnClickListener {
                    if (!getItem(absoluteAdapterPosition).email.isNullOrEmpty()) {
                        emailClickedListener?.invoke(getItem(absoluteAdapterPosition).email.toString())
                    }
                }
            }
        }

        fun bindData(contact: RegionalContactResponseItem) = binding.scope {
            val isNameVisible = !(contact.name.isNullOrEmpty() || contact.name.isNullOrBlank())
            val isLocationVisible = !(contact.location.isNullOrEmpty() || contact.location.isNullOrBlank())
            val isEmailVisible = !(contact.email.isNullOrEmpty() || contact.email.isNullOrBlank())
            val isPhoneVisible = !(contact.phone.isNullOrEmpty() || contact.phone.isNullOrBlank())

            layoutName.visible(isNameVisible)
            layoutLocation.visible(isLocationVisible)
            layoutEmail.visible(isEmailVisible)
            layoutPhone.visible(isPhoneVisible)
            itemView.visible(isNameVisible || isLocationVisible || isEmailVisible || isPhoneVisible)

            txtName.text = contact.name
            txtLocation.text = contact.location
            txtPhone.text = contact.phone
            txtEmail.text = contact.email
        }
    }
}

class RegionalContactItemDiffCallback : DiffUtil.ItemCallback<RegionalContactResponseItem>() {
    override fun areItemsTheSame(oldItem: RegionalContactResponseItem, newItem: RegionalContactResponseItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: RegionalContactResponseItem,
        newItem: RegionalContactResponseItem
    ): Boolean {
        return oldItem == newItem
    }
}
