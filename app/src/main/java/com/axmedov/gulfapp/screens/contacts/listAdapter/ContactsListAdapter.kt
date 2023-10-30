package com.axmedov.gulfapp.screens.contacts.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axmedov.gulfapp.data.entities.ContactData
import com.axmedov.gulfapp.databinding.ItemContactBinding
import com.axmedov.gulfapp.utils.scope

class ContactsListAdapter : ListAdapter<ContactData, ContactViewHolder>(ContactItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}

class ContactViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(contact: ContactData) = binding.scope {
        txtLocation.text = contact.location
        txtName.text = contact.name
        txtPhone.text = contact.phone
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