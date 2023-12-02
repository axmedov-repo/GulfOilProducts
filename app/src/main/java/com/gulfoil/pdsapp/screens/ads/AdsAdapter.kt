package com.gulfoil.pdsapp.screens.ads

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gulfoil.pdsapp.data.remote.responses.AdResponseItem
import com.gulfoil.pdsapp.screens.ads.item.AdsItemFragment
import com.gulfoil.pdsapp.utils.putArguments

class AdsAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle,
    private val list: List<AdResponseItem>
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int = list.size
    override fun createFragment(position: Int): Fragment {
        val fragment = AdsItemFragment()
        fragment.putArguments { putString("ADS_IMAGE", list[position].image) }
        return fragment
    }
}
