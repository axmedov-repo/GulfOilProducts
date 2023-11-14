package com.gulfoil.pdsapp.screens.contacts.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gulfoil.pdsapp.data.entities.ContactData
import com.gulfoil.pdsapp.databinding.ItemContactBinding
import com.gulfoil.pdsapp.utils.gone
import com.gulfoil.pdsapp.utils.scope

class ContactsListAdapter : ListAdapter<ContactData, ContactsListAdapter.ContactViewHolder>(ContactItemDiffCallback()) {

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
                    phoneClickedListener?.invoke(getItem(absoluteAdapterPosition).phone)
                }
                txtEmail.setOnClickListener {
                    emailClickedListener?.invoke(getItem(absoluteAdapterPosition).email)
                }
            }
        }

        fun bindData(contact: ContactData) = binding.scope {
            txtLocation.text = contact.location
            txtName.text = contact.name
            txtPhone.text = contact.phone

            if (contact.email.isEmpty() || contact.email.isBlank()) {
                txtEmail.gone()
            }
            txtEmail.text = contact.email
        }
    }
}

class ContactItemDiffCallback : DiffUtil.ItemCallback<ContactData>() {
    override fun areItemsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContactData, newItem: ContactData): Boolean {
        return oldItem == newItem
    }
}