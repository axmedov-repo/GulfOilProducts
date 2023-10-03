package com.axmedov.gulfapp.screens.old_variant.product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenProductsBinding
import com.axmedov.gulfapp.utils.productsList
import com.axmedov.gulfapp.utils.scope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsScreen : Fragment(R.layout.screen_products) {
    private val binding by viewBinding(ScreenProductsBinding::bind)
    private val adapter by lazy { ProductsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    private fun setViews() = binding.scope {
        rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProducts.adapter = adapter
        adapter.setItemClickedListener {
            findNavController().navigate(ProductsScreenDirections.actionProductsScreenToOilsScreen(it.productType))
        }
    }

    private fun setModels() = binding.scope {
        adapter.setData(productsList)
    }
}
