package com.gulfoil.pdsapp.screens.ads.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.databinding.ItemAdsBinding
import com.gulfoil.pdsapp.utils.scope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdsItemFragment : Fragment(R.layout.item_ads) {
    private val binding by viewBinding(ItemAdsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            imgAd.setImageResource(it.getInt("ADS_IMAGE"))
        }
    }
}
