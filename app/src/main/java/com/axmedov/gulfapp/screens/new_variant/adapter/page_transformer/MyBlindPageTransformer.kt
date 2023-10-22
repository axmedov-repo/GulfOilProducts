package com.axmedov.gulfapp.screens.new_variant.adapter.page_transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.utils.blindAnimation

class MyBlindPageTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val pageHeight = page.height
        val numberOfBlinds = 12 // Adjust the number of blinds as needed

        if (position < -1) {
            // This page is way off-screen to the left, set invisible.
            for (i in 0 until numberOfBlinds) {
                val blindView = page.findViewById<View>(R.id.blind_view_1 + i) // Adjust the ID accordingly
//                blindView.alpha = alpha
                blindView?.rotationY = 0f
            }
        } else if (position <= 1) {
            // The page is visible on the screen.
            val alpha = 1.0f - Math.abs(position)

            // Apply the blinds transition effect to each blind view.
            for (i in 0 until numberOfBlinds) {
                val blindView = page.findViewById<View>(R.id.blind_view_1 + i) // Adjust the ID accordingly
//                blindView.alpha = alpha
                blindView.blindAnimation()
            }
        } else {
            // This page is way off-screen to the right, set invisible.
            for (i in 0 until numberOfBlinds) {
                val blindView = page.findViewById<View>(R.id.blind_view_1 + i) // Adjust the ID accordingly
//                blindView.alpha = alpha
                blindView?.rotationY = 0f
            }
        }
    }
}
