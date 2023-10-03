package com.axmedov.gulfapp.utils

import android.content.res.Resources
import android.text.Spanned
import android.util.TypedValue
import androidx.core.text.HtmlCompat
import com.axmedov.gulfapp.app.App

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

fun Float.toPx(): Int {
    val r: Resources = App.instance.resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_PX, this, r.displayMetrics
    ).toInt()

    return px
}

fun Float.toSP(): Float {
    val r: Resources = App.instance.resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP, this, r.displayMetrics
    )

    return px
}

fun Int.dpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.pxToDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun hourInMilliseconds(hour: Int): Long {
    return hour * 3600000L
}

fun String.removeHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
