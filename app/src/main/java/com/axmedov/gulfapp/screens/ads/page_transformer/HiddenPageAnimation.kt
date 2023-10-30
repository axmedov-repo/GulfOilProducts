package com.axmedov.gulfapp.screens.ads.page_transformer

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.axmedov.gulfapp.R
import kotlin.math.abs

class HiddenPageAnimation() : ViewPager2.PageTransformer {
    private val numberOfBlinds: Int = 12

    override fun transformPage(page: View, position: Float) {
        val pageWidth = page.width
        val blindWidth = pageWidth / numberOfBlinds
        for (i in 0 until numberOfBlinds) {
            val blindView = page.findViewById<View>(R.id.blind_view_1 + i) // Adjust the ID accordingly
            val animationProgress = abs(position - 1.0f / numberOfBlinds * i)
            val alpha = 1.0f - animationProgress
            blindView.alpha = alpha
            blindView.translationX = (blindWidth * i).toFloat()
        }
    }
}
