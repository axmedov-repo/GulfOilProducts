package com.axmedov.gulfapp.screens.new_variant.adapter.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ItemAdsBinding
import com.axmedov.gulfapp.utils.scope

class AdsItemFragment : Fragment(R.layout.item_ads) {
    private val binding by viewBinding(ItemAdsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            imgAd.setImageResource(it.getInt("ADS_IMAGE"))
        }
    }
}
