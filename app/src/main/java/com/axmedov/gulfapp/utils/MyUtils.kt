package com.axmedov.gulfapp.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

//@SuppressLint("WrongConstant")
//fun Fragment.showFancyToast(
//    message: String,
//    duration: Int = FancyToast.LENGTH_SHORT,
//    toastType: Int = FancyToast.INFO
//) {
//    FancyToast.makeText(
//        App.instance,
//        message,
//        toastType,
//        duration,
//        false
//    ).show()
//}

fun CheckBox.checked(boolean: Boolean = true) {
    this.isChecked = boolean
}

fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}

fun timber(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

fun Fragment.putArguments(block: Bundle.() -> Unit): Fragment {
    val bundle = arguments ?: Bundle()
    block(bundle)
    arguments = bundle
    return this
}

val months = arrayOf(
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
)

fun getDate(milliSeconds: Long): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}

fun getTimeInFormat(milliseconds: Long): String {
    var inSeconds = milliseconds / 1000
    var hours = 0
    var minutes = 0
    var seconds = 0
    val resultTime = StringBuilder()

    if (inSeconds >= 3_600) {
        hours = (inSeconds / 3_600).toInt()
    }
    if ((inSeconds % 3_600) >= 60) {
        minutes = ((inSeconds % 3_600) / 60).toInt()
    }
    seconds = ((inSeconds % 3_600) % 60).toInt()
    resultTime.append(getCorrectHour(hours))
    resultTime.append(getCorrectMinute(minutes))
    resultTime.append(getCorrectSecond(seconds))
    return resultTime.toString()
}

private fun getCorrectHour(indicator: Int): String {
    return if (indicator <= 0) {
        ""
    } else if (indicator in 1..9) {
        "0$indicator:"
    } else "$indicator:"
}

private fun getCorrectMinute(indicator: Int): String {
    return if (indicator == 0) {
        "00:"
    } else if (indicator in 1..9) {
        "0$indicator:"
    } else "$indicator:"
}

private fun getCorrectSecond(indicator: Int): String {
    return if (indicator == 0) {
        "00"
    } else if (indicator in 1..9) {
        "0$indicator"
    } else "$indicator"
}

fun Fragment.getCurrentCountryCode(): String {
    val tm = requireActivity().getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.networkCountryIso
}

fun Activity.getCurrentCountryCode(): String {
    val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.networkCountryIso
}
