package com.axmedov.gulfapp.screens.contacts

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenContactsBinding
import com.axmedov.gulfapp.utils.contactsList
import com.axmedov.gulfapp.utils.scope

class ContactsScreen : Fragment(R.layout.screen_contacts) {
    private val binding by viewBinding(ScreenContactsBinding::bind)
    private val adapter by lazy { ContactsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    private fun setViews() = binding.scope {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        rvContacts.layoutManager = LinearLayoutManager(requireContext())
        rvContacts.adapter = adapter
    }

    private fun setModels() = binding.scope {
        adapter.setData(contactsList)
    }
}
