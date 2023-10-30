package com.axmedov.gulfapp.screens.ads

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.axmedov.gulfapp.data.entities.AdsData
import com.axmedov.gulfapp.screens.ads.item.AdsItemFragment
import com.axmedov.gulfapp.utils.putArguments

class AdsAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val list: List<AdsData>
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = list.size
    override fun createFragment(position: Int): Fragment =
        AdsItemFragment().putArguments { putInt("ADS_IMAGE", list[position].img) }
}