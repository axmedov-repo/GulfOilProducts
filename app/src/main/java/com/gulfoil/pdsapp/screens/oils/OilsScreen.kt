package com.gulfoil.pdsapp.screens.oils

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.activity.setInternetReconnectedListener
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.databinding.ScreenOilsBinding
import com.gulfoil.pdsapp.screens.ads.AdsAdapter
import com.gulfoil.pdsapp.screens.oils.viewmodel.OilsViewModel
import com.gulfoil.pdsapp.screens.oils.viewmodel.OilsViewModelImpl
import com.gulfoil.pdsapp.utils.hideKeyboard
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.showKeyboard
import com.gulfoil.pdsapp.utils.showMessageOnTopOfScreen
import com.gulfoil.pdsapp.utils.visible
import com.hadar.danny.horinzontaltransformers.DepthTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OilsScreen : Fragment(R.layout.screen_oils) {
    private val binding by viewBinding(ScreenOilsBinding::bind)
    private val viewModel: OilsViewModel by viewModels<OilsViewModelImpl>()
    private val args by navArgs<OilsScreenArgs>()
    private val oilsAdapter by lazy { OilsAdapter() }
    private var language: Languages = Languages.ENGLISH

    private lateinit var adsAdapter: AdsAdapter
    private var currentPage = 0
    private val delayMillis: Long = 3000
    private var job: Job? = null

    private var isKeyboardOpen = false
    private var searchingText = ""
    private var isSearching = false
    private var closingByUnFocusing: Boolean = false
    private var isUIVisible: Boolean = false

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
        isUIVisible = true
        getData()
    }

    private fun getData() {
        getOils()
        viewModel.getLanguage()
        viewModel.getAds()
    }

    private fun setViews() = binding.scope {
        adsVP.setPageTransformer(DepthTransformer())
        adsVP.isUserInputEnabled = false

        rvOils.layoutManager = LinearLayoutManager(requireContext())
        rvOils.adapter = oilsAdapter
        oilsAdapter.setItemClickedListener {
            hideKeyboard()
            isKeyboardOpen = false
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.isIconified = true
            findNavController().navigate(OilsScreenDirections.actionOilsScreenToPdfScreen(it.id))
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        imgLanguage.setOnClickListener {
            language = if (language == Languages.ENGLISH) Languages.RUSSIAN else Languages.ENGLISH
            viewModel.setLanguage(language)
            setData()
            getOils()
        }

        initSearchListener()
        searchView.setOnClickListener {
            isKeyboardOpen = true
        }
        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                hideKeyboard()
                closingByUnFocusing = true
                isKeyboardOpen = false
                CoroutineScope(Dispatchers.IO).launch {
                    delay(500)
                    closingByUnFocusing = false
                }
            }
        }

        searchIcon.setOnClickListener {
            if (isKeyboardOpen) {
                closingByUnFocusing = false
                hideKeyboard()
                searchView.clearFocus()
                isKeyboardOpen = false
            } else if (!closingByUnFocusing) {
                searchView.requestFocus()
                showKeyboard()
                isKeyboardOpen = true
            }
        }

        val closeButton: ImageView =
            binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        closeButton.setOnClickListener {
            searchingText = ""
            searchView.setQuery(null, false)
            searchView.clearFocus()
            getOils()
            hideKeyboard()
            isKeyboardOpen = false
        }

        refreshLayout.setOnRefreshListener {
            getData()
            refreshLayout.isRefreshing = false
        }
    }

    private fun startAutoScroll() {
        job = lifecycleScope.launch {
            delay(delayMillis)

            while (isUIVisible) {
                val itemCount = oilsAdapter.itemCount

                if (adsAdapter.itemCount != 0) {
                    if (currentPage == itemCount - 1) {
                        currentPage = 0
                        binding.adsVP.setCurrentItem(currentPage, false)
                        delay(delayMillis)
                    } else {
                        currentPage++
                        binding.adsVP.setCurrentItem(currentPage, true)
                        delay(delayMillis)
                    }
                }
            }
        }
    }

    private fun setModels() = binding.scope {
        viewModel.apply {
            oilsLiveData.observe(viewLifecycleOwner) {
                txtEmpty.visible(it.isEmpty())
                if (it.isNotEmpty()) {
                    oilsAdapter.setData(it)
                }
            }
            searchResponseLiveData.observe(viewLifecycleOwner) {
                txtEmpty.visible(it.isEmpty())
                if (it.isNotEmpty()) {
                    oilsAdapter.setData(it)
                }
            }
            progressLiveData.observe(viewLifecycleOwner) {
                progressBar.visible(it)
            }
            errorLiveData.observe(viewLifecycleOwner) {
                showMessageOnTopOfScreen(
                    if (language == Languages.RUSSIAN) getString(R.string.something_went_wrong_ru)
                    else getString(R.string.something_went_wrong_en)
                )
            }
            lastLanguageLiveData.observe(viewLifecycleOwner) {
                language = it
                setData()
                getOils()
            }
            adResponseLiveData.observe(viewLifecycleOwner) { adsList ->
                adsAdapter = AdsAdapter(childFragmentManager, lifecycle, adsList)
                adsVP.adapter = adsAdapter
                job?.cancel()
                if (adsList.isNotEmpty()) {
                    adsVP.setCurrentItem(0, false)
                    startAutoScroll()
                }
            }
        }
    }

    private fun initSearchListener() = binding.scope {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    if (query.isNotEmpty()) {
                        isSearching = searchingText != query
                        searchingText = query.trim()
                        search(searchingText)
                    } else {
                        searchingText = ""
                        getOils()
                    }
                    hideKeyboard()
                    isKeyboardOpen = false
                } else {
                    searchingText = ""
                    getOils()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isNotEmpty()) {
                        isSearching = searchingText != newText
                        searchingText = newText.trim()
                        search(searchingText)
                    } else {
                        searchingText = ""
                        getOils()
                    }
                } else {
                    searchingText = ""
                    getOils()
                }
                return true
            }
        })
    }

    private fun search(text: String) {
        viewModel.searchOil(args.productId, text)
    }

    private fun getOils() {
        if (searchingText.isNotEmpty()) {
            search(searchingText)
        } else {
            viewModel.getOils(args.productId)
        }
    }

    private fun setData() = binding.scope {
        if (language == Languages.ENGLISH) {
            imgLanguage.setImageResource(R.drawable.ic_flag_en)
            txtEmpty.text = getString(R.string.txt_empty_en)
            txtTitle.text = getString(R.string.txt_gulf_oil_products_en)
            searchView.queryHint = getString(R.string.txt_search_en)
        } else {
            imgLanguage.setImageResource(R.drawable.ic_flag_ru)
            txtTitle.text = getString(R.string.txt_gulf_oil_products_ru)
            txtEmpty.text = getString(R.string.txt_empty_ru)
            searchView.queryHint = getString(R.string.txt_search_ru)
        }
    }

    override fun onPause() {
        super.onPause()
        isUIVisible = false
        hideKeyboard()
        job?.cancel()
        currentPage = 0
        isKeyboardOpen = false
    }
}
