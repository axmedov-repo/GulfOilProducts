package com.gulfoil.pdsapp.screens.contacts.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gulfoil.pdsapp.data.remote.responses.RegionalContactResponseItem
import com.gulfoil.pdsapp.databinding.ItemContactBinding
import com.gulfoil.pdsapp.utils.gone
import com.gulfoil.pdsapp.utils.scope

class ContactsListAdapter :
    ListAdapter<RegionalContactResponseItem, ContactsListAdapter.ContactViewHolder>(ContactItemDiffCallback()) {

    private var phoneClickedListener: ((String) -> Unit)? = null
    fun setPhoneClickedListener(f: (String) -> Unit) {
        phoneClickedListener = f
    }

    private var emailClickedListener: ((String) -> Unit)? = null
    fun setEmailClickedListener(f: (String) -> Unit) {
        emailClickedListener = f
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

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
            txtLocation.text = contact.location
            txtName.text = contact.name
            txtPhone.text = contact.phone
            txtEmail.text = contact.email

            if (contact.email.isNullOrEmpty() || contact.email.isNullOrBlank()) {
                txtEmail.gone()
            }
            if (contact.phone.isNullOrEmpty() || contact.phone.isNullOrBlank()) {
                txtPhone.gone()
            }
        }
    }
}

class ContactItemDiffCallback : DiffUtil.ItemCallback<RegionalContactResponseItem>() {
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