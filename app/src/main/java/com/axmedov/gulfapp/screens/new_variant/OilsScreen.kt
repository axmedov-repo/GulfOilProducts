package com.axmedov.gulfapp.screens.new_variant

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.data.enums.Languages
import com.axmedov.gulfapp.data.enums.ProductTypes
import com.axmedov.gulfapp.databinding.ScreenOilsBinding
import com.axmedov.gulfapp.utils.passengerCarListEn
import com.axmedov.gulfapp.utils.scope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OilsScreen : Fragment(R.layout.screen_oils) {
    private val binding by viewBinding(ScreenOilsBinding::bind)
    private val args by navArgs<OilsScreenArgs>()
    private val adapter by lazy { OilsAdapter() }
    private var language: Languages = Languages.ENGLISH

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)
        setViews()
        setModels()
    }

    private fun setViews() = binding.scope {
        rvProducts.layoutManager = LinearLayoutManager(requireContext())
        rvProducts.adapter = adapter

        adapter.setItemClickedListener {
            findNavController().navigate(OilsScreenDirections.actionOilsScreenToPdfScreen(it.pdsLink))
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        imgLanguage.setOnClickListener {
            if (language == Languages.ENGLISH) {
                imgLanguage.setImageResource(R.drawable.ic_flag_ru)
                txtTitle.text = getString(R.string.txt_gulf_products_ru)
                searchView.queryHint = getString(R.string.txt_search_ru)
                language = Languages.RUSSIAN
            } else {
                imgLanguage.setImageResource(R.drawable.ic_flag_en)
                txtTitle.text = getString(R.string.txt_gulf_products_en)
                searchView.queryHint = getString(R.string.txt_search_en)
                language = Languages.ENGLISH
            }
        }
    }

    private fun setModels() = binding.scope {
        when (args.productType) {
            ProductTypes.PASSENGER_CAR -> {
                adapter.setData(passengerCarListEn)
            }

            ProductTypes.COMMERCIAL -> {

            }

            ProductTypes.AUTOMATIC_TRANSMISSION -> {

            }

            ProductTypes.RADIATOR_COOLANT -> {

            }
        }
    }
}
