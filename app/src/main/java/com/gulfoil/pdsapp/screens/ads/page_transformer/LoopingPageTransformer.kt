package com.gulfoil.pdsapp.screens.ads.page_transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class LoopingPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        if (position < -1) {
            // This page is way off-screen to the left.
            page.alpha = 0f
        } else if (position <= 1) {
            // The page is visible on the screen.
            page.alpha = 1f

            // Apply a translation effect to simulate looping.
            val width = page.width.toFloat()
            page.translationX = -width * position
        } else {
            // This page is way off-screen to the right.
            page.alpha = 0f
        }
    }
}
