package com.axmedov.gulfapp.screens.new_variant.adapter.page_transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class JalousiePageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageHeight = page.height
        if (position < -1) {
            // This page is way off-screen to the top.
            page.alpha = 0f
        } else if (position <= 1) {
            // The page is visible on the screen.
            page.alpha = 1f

            // Apply a top-to-bottom jalousie animation effect.
            page.translationY = -position * pageHeight
        } else {
            // This page is way off-screen to the bottom.
            page.alpha = 0f
        }
    }
}
