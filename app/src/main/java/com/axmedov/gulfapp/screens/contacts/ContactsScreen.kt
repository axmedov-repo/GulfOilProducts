package com.axmedov.gulfapp.screens.contacts

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.enums.CountriesEnum
import com.axmedov.gulfapp.data.enums.Languages
import com.axmedov.gulfapp.databinding.ScreenContactsBinding
import com.axmedov.gulfapp.screens.contacts.listAdapter.ContactsListAdapter
import com.axmedov.gulfapp.screens.contacts.viewmodel.ContactsViewModel
import com.axmedov.gulfapp.screens.contacts.viewmodel.ContactsViewModelImpl
import com.axmedov.gulfapp.utils.contactsList
import com.axmedov.gulfapp.utils.scope

class ContactsScreen : Fragment(R.layout.screen_contacts) {
    private val binding by viewBinding(ScreenContactsBinding::bind)
    private val viewModel: ContactsViewModel by viewModels<ContactsViewModelImpl>()
    private val contactsAdapter by lazy { ContactsAdapter() }
    private val contactsListAdapter by lazy { ContactsListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLanguage()
        viewModel.getCountry()
    }

    private fun setViews() = binding.scope {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        rvContacts.layoutManager = LinearLayoutManager(requireContext())
        rvContacts.adapter = contactsListAdapter

//        spinnerView.setItems(CountriesEnum.values().map { it.title })
//        spinnerView.setOnFocusChangeListener { view, b ->
//            if (!b) {
//                spinnerView.dismiss()
//            }
//        }
//        spinnerView.setOnSpinnerItemSelectedListener<String> { oldIndex, oldItem, newIndex, newItem ->
//            showToast(newItem)
//        }

        val spinnerAdapter =
            ArrayAdapter(requireContext(), R.layout.item_spinner, CountriesEnum.values().map { it.title })
        simpleSpinner.adapter = spinnerAdapter
        simpleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val country = CountriesEnum.values()[position]
                viewModel.setCountry(country)
                contactsListAdapter.submitList(contactsList.filter { it.country == country })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle when nothing is selected
            }
        }

        txtChangeCountry.setOnClickListener {
            simpleSpinner.performClick()
        }
    }

    private fun setModels() = binding.scope {
        viewModel.countryLiveData.observe(viewLifecycleOwner) { country ->
            simpleSpinner.setSelection(CountriesEnum.values().indexOf(country))
            contactsListAdapter.submitList(contactsList.filter { it.country == country })
        }
        viewModel.lastLanguageLiveData.observe(viewLifecycleOwner) {
            setData(it)
        }
    }

    private fun setData(language: Languages) = binding.scope {
        when (language) {
            Languages.ENGLISH -> {
                txtTitle.text = getString(R.string.txt_contacts_en)
                txtChangeCountry.text = getString(R.string.txt_change_country_en)
            }

            Languages.RUSSIAN -> {
                txtTitle.text = getString(R.string.txt_contacts_ru)
                txtChangeCountry.text = getString(R.string.txt_change_country_ru)
            }
        }
    }

//    override fun onPause() {
//        super.onPause()
//        binding.spinnerView.dismiss()
//    }
}
