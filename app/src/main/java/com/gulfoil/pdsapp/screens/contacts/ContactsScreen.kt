package com.gulfoil.pdsapp.screens.contacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.databinding.ScreenContactsBinding
import com.gulfoil.pdsapp.screens.contacts.adapters.PublicContactsAdapter
import com.gulfoil.pdsapp.screens.contacts.adapters.RegionalContactsAdapter
import com.gulfoil.pdsapp.screens.contacts.viewmodel.ContactsViewModel
import com.gulfoil.pdsapp.screens.contacts.viewmodel.ContactsViewModelImpl
import com.gulfoil.pdsapp.setInternetReconnectedListener
import com.gulfoil.pdsapp.utils.getCurrentCountryCode
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsScreen : Fragment(R.layout.screen_contacts) {
    private val binding by viewBinding(ScreenContactsBinding::bind)
    private val viewModel: ContactsViewModel by viewModels<ContactsViewModelImpl>()
    private val regionalContactsAdapter by lazy { RegionalContactsAdapter() }
    private val publicContactsAdapter by lazy { PublicContactsAdapter() }

    init {
        setInternetReconnectedListener {
            getData()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {
        viewModel.getLanguage()
        viewModel.getPublicContacts()
        viewModel.getRegionalContacts(getCurrentCountryCode())
    }

    private fun setViews() = binding.scope {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        rvRegionalContacts.layoutManager = LinearLayoutManager(requireContext())
        rvRegionalContacts.adapter = regionalContactsAdapter
        regionalContactsAdapter.setPhoneClickedListener {
            openPhone(it)
        }
        regionalContactsAdapter.setEmailClickedListener {
            openEmail(it)
        }

        rvPublicContacts.layoutManager = LinearLayoutManager(requireContext())
        rvPublicContacts.adapter = publicContactsAdapter
        publicContactsAdapter.setPhoneClickedListener {
            openPhone(txtHeadContactPhone.text.toString())
        }
        publicContactsAdapter.setEmailClickedListener {
            openEmail(txtHeadContactEmail.text.toString())
        }
        publicContactsAdapter.setWebsiteClickedListener {
            openWebsite(txtHeadContactWebsite.text.toString())
        }

        refreshLayout.setOnRefreshListener {
            getData()
        }
    }

    private fun openPhone(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        startActivity(intent)
    }

    private fun openEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$email")
        startActivity(intent)
    }

    private fun openWebsite(siteUrl: String) {
        var url = siteUrl
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://$url"
        }
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun setModels() = binding.scope {
        viewModel.publicContactsLiveData.observe(viewLifecycleOwner) {
            publicContactsAdapter.submitList(it)
        }
        viewModel.regionalContactsLiveData.observe(viewLifecycleOwner) {
            regionalContactsAdapter.submitList(it)
        }
        viewModel.lastLanguageLiveData.observe(viewLifecycleOwner) {
            setData(it)
        }
        viewModel.progressLiveData.observe(viewLifecycleOwner) {
            progressBar.visible(it)
            refreshLayout.isRefreshing = it
        }
        viewModel.connectionLiveData.observe(viewLifecycleOwner) { isConnected ->
            layoutHeadContact.visible(!isConnected)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            // TODO: Hande Error
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
}
