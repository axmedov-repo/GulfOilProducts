package com.gulfoil.pdsapp.utils

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.gulfoil.pdsapp.R
import com.gulfoil.pdsapp.app.App

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

fun View.visible(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setMarginsInDp(left: Float, top: Float, right: Float, bottom: Float) {
    if (this.layoutParams is ViewGroup.MarginLayoutParams) {
        val p = this.layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(left.toPx(), top.toPx(), right.toPx(), bottom.toPx())
        this.requestLayout()
    }
}

fun String.normalCase(): String {
    return this.lowercase()[0].uppercase()
}

fun TextInputLayout.enableError() {
    this.isErrorEnabled = true
}

fun TextInputLayout.disableError() {
    this.boxStrokeColor = ContextCompat.getColor(App.instance, R.color.blue)
    this.isErrorEnabled = false
}

fun TextInputLayout.enableCorrect() {
    this.isErrorEnabled = false
    this.boxStrokeColor = ContextCompat.getColor(App.instance, R.color.orange)
}

fun Button.enable() {
    this.isEnabled = true
}

fun Button.disable() {
    this.isEnabled = false
}

fun AppCompatButton.enable() {
    this.isEnabled = true
}

fun AppCompatButton.disable() {
    this.isEnabled = false
}

//fun LinearLayoutCompat.lighting(
//    lightingBackground: Int = R.drawable.bg_lighting,
//    defaultBackground: Int = R.drawable.blue
//) {
//    this.animate().setDuration(350).withEndAction {
//        this.setBackgroundResource(lightingBackground)
//        this.animate().setDuration(350).withEndAction {
//            this.setBackgroundResource(defaultBackground)
//        }.start()
//    }.start()
//}

fun TextView.lighting(
    lightingColor: Int = ContextCompat.getColor(App.instance, R.color.orange),
    defaultColor: Int = ContextCompat.getColor(App.instance, R.color.blue)
) {
    this.animate().setDuration(350).withEndAction {
        this.setTextColor(lightingColor)
        this.animate().setDuration(350).withEndAction {
            this.setTextColor(defaultColor)
        }.start()
    }.start()
}

fun ImageView.animateAlpha() {
    this.animate().setDuration(2000).withEndAction {
        this.alpha = 0f
    }.start()
}
