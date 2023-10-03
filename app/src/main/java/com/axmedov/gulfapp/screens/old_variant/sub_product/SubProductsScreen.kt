package com.axmedov.gulfapp.screens.old_variant.sub_product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenSubProductsBinding
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.showToast
import com.axmedov.gulfapp.utils.subProductsList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubProductsScreen : Fragment(R.layout.screen_sub_products) {
    private val binding by viewBinding(ScreenSubProductsBinding::bind)
    private val adapter by lazy { SubProductsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    private fun setViews() = binding.scope {
        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = adapter
        adapter.setItemClickedListener {
            showToast(it.name)
        }
    }

    private fun setModels() = binding.scope {
        adapter.setData(subProductsList)
    }
}
