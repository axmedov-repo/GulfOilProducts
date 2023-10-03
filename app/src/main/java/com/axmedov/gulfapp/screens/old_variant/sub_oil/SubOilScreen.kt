package com.axmedov.gulfapp.screens.old_variant.sub_oil

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenSubOilBinding
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.showToast
import com.axmedov.gulfapp.utils.subOilsList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubOilScreen : Fragment(R.layout.screen_sub_oil) {
    private val binding by viewBinding(ScreenSubOilBinding::bind)
    private val adapter by lazy { SubOilAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    private fun setViews() = binding.scope {
        rvProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        rvProducts.adapter = adapter
        adapter.setItemClickedListener {
            showToast(it.name)
        }
    }

    private fun setModels() = binding.scope {
        adapter.setData(subOilsList)
    }
}
