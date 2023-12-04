package com.gulfoil.pdsapp.screens.contacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gulfoil.pdsapp.data.remote.responses.PublicContactResponseItem
import com.gulfoil.pdsapp.databinding.ItemPublicContactBinding
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.visible

class PublicContactsAdapter :
    ListAdapter<PublicContactResponseItem, PublicContactsAdapter.ContactViewHolder>(PublicContactItemDiffCallback()) {

    private var phoneClickedListener: ((String) -> Unit)? = null
    fun setPhoneClickedListener(f: (String) -> Unit) {
        phoneClickedListener = f
    }

    private var emailClickedListener: ((String) -> Unit)? = null
    fun setEmailClickedListener(f: (String) -> Unit) {
        emailClickedListener = f
    }

    private var websiteClickedListener: ((String) -> Unit)? = null
    fun setWebsiteClickedListener(f: (String) -> Unit) {
        websiteClickedListener = f
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemPublicContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ItemPublicContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.scope {
                txtHeadContactPhone.setOnClickListener {
                    if (!getItem(absoluteAdapterPosition).phone.isNullOrEmpty()) {
                        phoneClickedListener?.invoke(getItem(absoluteAdapterPosition).phone.toString())
                    }
                }
                txtHeadContactEmail.setOnClickListener {
                    if (!getItem(absoluteAdapterPosition).email.isNullOrEmpty()) {
                        emailClickedListener?.invoke(getItem(absoluteAdapterPosition).email.toString())
                    }
                }
                txtHeadContactWebsite.setOnClickListener {
                    if (!getItem(absoluteAdapterPosition).website.isNullOrEmpty()) {
                        websiteClickedListener?.invoke(getItem(absoluteAdapterPosition).website.toString())
                    }
                }
            }
        }

        fun bindData(contact: PublicContactResponseItem) = binding.scope {
            val isLocationVisible = !(contact.address.isNullOrEmpty() || contact.address.isNullOrBlank())
            val isWebsiteVisible = !(contact.website.isNullOrEmpty() || contact.website.isNullOrBlank())
            val isEmailVisible = !(contact.email.isNullOrEmpty() || contact.email.isNullOrBlank())
            val isPhoneVisible = !(contact.phone.isNullOrEmpty() || contact.phone.isNullOrBlank())

            layoutHeadContactLocation.visible(isLocationVisible)
            layoutHeadContactWebsite.visible(isWebsiteVisible)
            layoutHeadContactEmail.visible(isEmailVisible)
            layoutHeadContactPhone.visible(isPhoneVisible)
            itemViewHeadContact.visible(isLocationVisible || isWebsiteVisible || isEmailVisible || isPhoneVisible)

            txtHeadContactLocation.text = contact.address
            txtHeadContactWebsite.text = contact.website
            txtHeadContactEmail.text = contact.email
            txtHeadContactPhone.text = contact.phone
        }
    }
}

class PublicContactItemDiffCallback : DiffUtil.ItemCallback<PublicContactResponseItem>() {
    override fun areItemsTheSame(oldItem: PublicContactResponseItem, newItem: PublicContactResponseItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: PublicContactResponseItem,
        newItem: PublicContactResponseItem
    ): Boolean {
        return oldItem == newItem
    }
}