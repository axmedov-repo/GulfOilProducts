package com.axmedov.gulfapp.screens.ads.page_transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.axmedov.gulfapp.R

class PowerPointBlindsTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        val numberOfBlinds = 6 // Adjust the number of blinds as needed
        if (position < -1) {
            // This page is way off-screen to the left, set invisible.
            page.alpha = 0f
        } else if (position <= 1) {
            // The page is visible on the screen.
            val alpha = 1.0f - Math.abs(position)

            // Apply the blinds transition effect to each blind view.
            for (i in 0 until numberOfBlinds) {
                val blindView = page.findViewById<View>(R.id.blind_view_1 + i) // Adjust the ID accordingly
                blindView.alpha = alpha
            }
        } else {
            // This page is way off-screen to the right, set invisible.
            page.alpha = 0f
        }
    }
}

