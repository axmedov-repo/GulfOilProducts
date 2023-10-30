package com.axmedov.gulfapp.screens.product

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.entities.AdsData
import com.axmedov.gulfapp.data.entities.ProductData
import com.axmedov.gulfapp.data.enums.Languages
import com.axmedov.gulfapp.data.enums.ProductTypes
import com.axmedov.gulfapp.databinding.ScreenProductsBinding
import com.axmedov.gulfapp.screens.ads.AdsAdapter
import com.axmedov.gulfapp.screens.product.view_model.ProductsViewModel
import com.axmedov.gulfapp.screens.product.view_model.ProductsViewModelImpl
import com.axmedov.gulfapp.utils.adsDataList
import com.axmedov.gulfapp.utils.productsListEn
import com.axmedov.gulfapp.utils.productsListRu
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.visible
import com.hadar.danny.horinzontaltransformers.DepthTransformer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//@AndroidEntryPoint
class ProductsScreen : Fragment(R.layout.screen_products) {
    private val binding by viewBinding(ScreenProductsBinding::bind)
    private val viewModel: ProductsViewModel by viewModels<ProductsViewModelImpl>()
    private var language = Languages.ENGLISH
    private val adapter by lazy { ProductsAdapter() }

    private lateinit var adsAdapter: AdsAdapter
    private val adsList = ArrayList<AdsData>()
    private var handler: Handler? = null
    private var currentPage = 0
    private val delayMillis: Long = 3000
    private var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLanguage()
        startAutoScroll()
    }

    private fun setViews() = binding.scope {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })

        adsList.clear()
        adsList.addAll(adsDataList)
        adsAdapter = AdsAdapter(childFragmentManager, lifecycle, adsList)
        vp.adapter = adsAdapter
        vp.setPageTransformer(DepthTransformer())
        vp.isUserInputEnabled = false
        handler = Handler()

//        rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = adapter
        adapter.setItemClickedListener {
            if (it.productType == ProductTypes.CONTACT) {
                findNavController().navigate(ProductsScreenDirections.actionProductsScreenToContactsScreen())
            } else {
                findNavController().navigate(
                    ProductsScreenDirections.actionProductsScreenToOilsScreen(
                        it.productType
                    )
                )
            }
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
    }

    private fun startAutoScroll() {
        job = CoroutineScope(Dispatchers.Main).launch {
            delay(delayMillis)

            while (true) {
                val itemCount = adsAdapter.itemCount
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

    private fun setData() = binding.scope {
        var list: List<ProductData> = ArrayList()
        when (language) {
            Languages.ENGLISH -> {
                imgLanguage.setImageResource(R.drawable.ic_flag_en)
                txtEmpty.text = getString(R.string.txt_empty_en)
                list = productsListEn
                txtTitle.text = getString(R.string.txt_gulf_oil_products_en)
            }

            Languages.RUSSIAN -> {
                imgLanguage.setImageResource(R.drawable.ic_flag_ru)
                txtEmpty.text = getString(R.string.txt_empty_ru)
                list = productsListRu
                txtTitle.text = getString(R.string.txt_gulf_oil_products_ru)
            }
        }

        txtEmpty.visible(list.isEmpty())
        adapter.setData(list)
    }

    override fun onPause() {
        super.onPause()
        job?.cancel()
    }
}
