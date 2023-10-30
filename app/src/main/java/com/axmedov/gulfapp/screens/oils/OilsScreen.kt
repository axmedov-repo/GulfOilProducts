package com.axmedov.gulfapp.screens.oils

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.entities.AdsData
import com.axmedov.gulfapp.data.entities.NewOilData
import com.axmedov.gulfapp.data.enums.Languages
import com.axmedov.gulfapp.data.enums.ProductTypes
import com.axmedov.gulfapp.databinding.ScreenOilsBinding
import com.axmedov.gulfapp.screens.ads.AdsAdapter
import com.axmedov.gulfapp.screens.oils.viewmodel.OilsViewModel
import com.axmedov.gulfapp.screens.oils.viewmodel.OilsViewModelImpl
import com.axmedov.gulfapp.utils.adsDataList
import com.axmedov.gulfapp.utils.automaticTransmissionListEn
import com.axmedov.gulfapp.utils.automaticTransmissionListRu
import com.axmedov.gulfapp.utils.commercialCarListEn
import com.axmedov.gulfapp.utils.commercialCarListRu
import com.axmedov.gulfapp.utils.hideKeyboard
import com.axmedov.gulfapp.utils.hydraulicBrakeFluidListEn
import com.axmedov.gulfapp.utils.hydraulicBrakeFluidListRu
import com.axmedov.gulfapp.utils.passengerCarListEn
import com.axmedov.gulfapp.utils.passengerCarListRu
import com.axmedov.gulfapp.utils.radiatorCoolantListEn
import com.axmedov.gulfapp.utils.radiatorCoolantListRu
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.showKeyboard
import com.axmedov.gulfapp.utils.timber
import com.axmedov.gulfapp.utils.visible
import com.hadar.danny.horinzontaltransformers.DepthTransformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@AndroidEntryPoint
class OilsScreen : Fragment(R.layout.screen_oils) {
    private val binding by viewBinding(ScreenOilsBinding::bind)
    private val viewModel: OilsViewModel by viewModels<OilsViewModelImpl>()
    private val args by navArgs<OilsScreenArgs>()
    private val oilsAdapter by lazy { OilsAdapter() }
    private var language: Languages = Languages.ENGLISH
    private var list: List<NewOilData> = ArrayList()

    private lateinit var adsAdapter: AdsAdapter
    private val adsList = ArrayList<AdsData>()
    private var handler: Handler? = null
    private var currentPage = 0
    private val delayMillis: Long = 3000
    private var job: Job? = null

    private var isKeyboardOpen = false
    private var queryHint: String = ""
    private var searchingText = ""
    private var isSearching = false
    private var closingByUnFocusing: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    override fun onResume() {
        super.onResume()
        timber("OnResume", "sdjksjdkds")
        viewModel.getLanguage()
        startAutoScroll()
    }

    private fun setViews() = binding.scope {
        adsList.clear()
        adsList.addAll(adsDataList)
        adsAdapter = AdsAdapter(childFragmentManager, lifecycle, adsList)
        vp.adapter = adsAdapter
        vp.setPageTransformer(DepthTransformer())
        vp.isUserInputEnabled = false
        handler = Handler()

        rvOils.layoutManager = LinearLayoutManager(requireContext())
        rvOils.adapter = oilsAdapter
        oilsAdapter.setItemClickedListener {
            hideKeyboard()
            isKeyboardOpen = false
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.isIconified = true
            findNavController().navigate(OilsScreenDirections.actionOilsScreenToPdfScreen(it.pdsLink))
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        imgLanguage.setOnClickListener {
            if (language == Languages.ENGLISH) {
                language = Languages.RUSSIAN
                setData()
                viewModel.setLanguage(language)
            } else {
                language = Languages.ENGLISH
                setData()
                viewModel.setLanguage(language)
            }
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

        val closeButton = binding.searchView.findViewById(androidx.appcompat.R.id.search_close_btn) as ImageView
        closeButton.setOnClickListener {
            timber("Closed", "jskdjksdjk")
            searchingText = ""
            searchView.setQuery(null, false)
            searchView.clearFocus()
            setData()
            hideKeyboard()
            isKeyboardOpen = false
        }
    }

    private fun startAutoScroll() {
//        val runnable = object : Runnable {
//            override fun run() {
//                val itemCount = oilsAdapter.itemCount
//                if (currentPage == itemCount - 1) {
//                    currentPage = 0
//                    binding.vp.setCurrentItem(currentPage, false)
//                    handler!!.postDelayed(this, delayMillis)
//                } else {
//                    currentPage++
//                    binding.vp.setCurrentItem(currentPage, true)
//                    handler!!.postDelayed(this, delayMillis)
//                }
//            }
//        }
//        handler!!.postDelayed(runnable, delayMillis)

        job = CoroutineScope(Dispatchers.Main).launch {
            delay(delayMillis)

            while (true) {
                val itemCount = oilsAdapter.itemCount
                if (currentPage == itemCount - 1) {
                    currentPage = 0
                    binding.vp.setCurrentItem(currentPage, false)
                    delay(delayMillis)
                } else {
                    currentPage++
                    binding.vp.setCurrentItem(currentPage, true)
                    delay(delayMillis)
                }
            }
        }
    }

    private fun setModels() = binding.scope {
        viewModel.lastLanguageLiveData.observe(viewLifecycleOwner) {
            language = it
            setData()
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
                        setData()
                    }
                    hideKeyboard()
                    isKeyboardOpen = false
                } else {
                    searchingText = ""
                    setData()
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
                        setData()
                    }
                } else {
                    searchingText = ""
                    setData()
                }
                return true
            }
        })
    }

    private fun search(text: String) {
        val filteredList = list.filter { it.name.lowercase().contains(text.lowercase()) }
        binding.txtEmpty.visible(filteredList.isEmpty())
        oilsAdapter.setData(filteredList)
    }

    private fun setData() = binding.scope {
        if (language == Languages.ENGLISH) {
            imgLanguage.setImageResource(R.drawable.ic_flag_en)
            txtEmpty.text = getString(R.string.txt_empty_en)
            txtTitle.text = getString(R.string.txt_gulf_oil_products_en)
            queryHint = getString(R.string.txt_search_en)

            when (args.productType) {
                ProductTypes.PASSENGER_CAR -> {
                    list = passengerCarListEn
                }

                ProductTypes.COMMERCIAL -> {
                    list = commercialCarListEn
                }

                ProductTypes.AUTOMATIC_TRANSMISSION -> {
                    list = automaticTransmissionListEn
                }

                ProductTypes.HYDRAULIC_BRAKE_FLUID -> {
                    list = hydraulicBrakeFluidListEn
                }

                ProductTypes.RADIATOR_COOLANT -> {
                    list = radiatorCoolantListEn
                }

                else -> {

                }
            }
        } else {
            imgLanguage.setImageResource(R.drawable.ic_flag_ru)
            txtTitle.text = getString(R.string.txt_gulf_oil_products_ru)
            txtEmpty.text = getString(R.string.txt_empty_ru)
            queryHint = getString(R.string.txt_search_ru)

            when (args.productType) {
                ProductTypes.PASSENGER_CAR -> {
                    list = passengerCarListRu
                }

                ProductTypes.COMMERCIAL -> {
                    list = commercialCarListRu
                }

                ProductTypes.AUTOMATIC_TRANSMISSION -> {
                    list = automaticTransmissionListRu
                }

                ProductTypes.HYDRAULIC_BRAKE_FLUID -> {
                    list = hydraulicBrakeFluidListRu
                }

                ProductTypes.RADIATOR_COOLANT -> {
                    list = radiatorCoolantListRu
                }

                else -> {

                }
            }
        }

        searchView.queryHint = queryHint
        txtEmpty.visible(list.isEmpty())

        if (searchingText.isNotEmpty()) {
            search(searchingText)
        } else {
            oilsAdapter.setData(list)
        }
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
        job?.cancel()
        isKeyboardOpen = false
    }
}
