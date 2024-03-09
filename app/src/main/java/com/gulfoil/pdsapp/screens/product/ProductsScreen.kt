package com.gulfoil.pdsapp.screens.product

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.data.enums.Languages
import com.gulfoil.pdsapp.databinding.ScreenProductsBinding
import com.gulfoil.pdsapp.screens.ads.AdsAdapter
import com.gulfoil.pdsapp.screens.product.view_model.ProductsViewModel
import com.gulfoil.pdsapp.screens.product.view_model.ProductsViewModelImpl
import com.gulfoil.pdsapp.activity.setInternetReconnectedListener
import com.gulfoil.pdsapp.utils.scope
import com.gulfoil.pdsapp.utils.visible
import com.hadar.danny.horinzontaltransformers.DepthTransformer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsScreen : Fragment(R.layout.screen_products) {
    private val binding by viewBinding(ScreenProductsBinding::bind)
    private val viewModel: ProductsViewModel by viewModels<ProductsViewModelImpl>()
    private var language = Languages.ENGLISH
    private val adapter by lazy { ProductsAdapter() }

    private lateinit var adsAdapter: AdsAdapter
    private var currentPage = 0
    private val delayMillis: Long = 3000
    private var job: Job? = null
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
        viewModel.getLanguage()
        viewModel.getProducts()
        viewModel.getAds()
    }

    private fun setViews() = binding.scope {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
        adsVP.setPageTransformer(DepthTransformer())
        adsVP.isUserInputEnabled = false

        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = adapter
        adapter.setItemClickedListener {
            it.id?.let { id ->
                findNavController().navigate(ProductsScreenDirections.actionProductsScreenToOilsScreen(id))
            }
        }

        layoutContact.setOnClickListener {
            findNavController().navigate(ProductsScreenDirections.actionProductsScreenToContactsScreen())
        }

        imgLanguage.setOnClickListener {
            if (language == Languages.ENGLISH) {
                language = Languages.RUSSIAN
                viewModel.setLanguage(language)
                viewModel.getProducts()
                setData()
            } else {
                language = Languages.ENGLISH
                viewModel.setLanguage(language)
                viewModel.getProducts()
                setData()
            }
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
                val itemCount = adsAdapter.itemCount

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
            productsLiveData.observe(viewLifecycleOwner) {
                txtEmpty.visible(it.isEmpty())
                if (it.isNotEmpty()) {
                    adapter.setData(it)
                    rvProducts.scheduleLayoutAnimation()
                }
            }
            progressLiveData.observe(viewLifecycleOwner) {
                progressBar.visible(it)
            }
            errorLiveData.observe(viewLifecycleOwner) {
                // TODO: Handle error
            }
            lastLanguageLiveData.observe(viewLifecycleOwner) {
                language = it
                setData()
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

    private fun setData() = binding.scope {
        when (language) {
            Languages.ENGLISH -> {
                imgLanguage.setImageResource(R.drawable.ic_flag_en)
                txtEmpty.text = getString(R.string.txt_empty_en)
                txtTitle.text = getString(R.string.txt_gulf_oil_products_en)
                txtNameContact.text = getString(R.string.txt_contacts_en)
            }

            Languages.RUSSIAN -> {
                imgLanguage.setImageResource(R.drawable.ic_flag_ru)
                txtEmpty.text = getString(R.string.txt_empty_ru)
                txtTitle.text = getString(R.string.txt_gulf_oil_products_ru)
                txtNameContact.text = getString(R.string.txt_contacts_ru)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        job?.cancel()
        currentPage = 0
        isUIVisible = false
    }
}
