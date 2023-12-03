package com.gulfoil.pdsapp.utils

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

//fun showErrorFlashBar(
//    activity: Activity,
//    title: String,
//    message: String,
//    flashBarType: FlashBarTypes,
//    gravity: Flashbar.Gravity = Flashbar.Gravity.BOTTOM
//) {
//    Flashbar.Builder(activity)
//        .gravity(gravity)
//        .title(title)
//        .titleSizeInSp(18f)
//        .message(message)
//        .messageSizeInSp(16f)
//        .showIcon()
//        .duration(4000)
//        .icon(flashBarType.icon)
//        .iconColorFilterRes(
//            when (flashBarType) {
//                FlashBarTypes.ERROR -> {
//                    R.color.white
//                }
//
//                FlashBarTypes.ALERT -> {
//                    R.color.white
//                }
//
//                FlashBarTypes.INFO -> {
//                    R.color.white
//                }
//            }
//        )
//        .backgroundColorRes(R.color.baseColor)
//        .enterAnimation(
//            FlashAnim.with(App.instance)
//                .animateBar()
//                .duration(750)
//                .alpha()
//                .overshoot()
//        )
//        .exitAnimation(
//            FlashAnim.with(App.instance)
//                .animateBar()
//                .duration(400)
//                .accelerateDecelerate()
//        )
//        .build()
//        .show()
//}

private var showMessageOnTopOfScreenListener: ((String) -> Unit)? = null
fun setShowMessageOnTopOfScreenListener(f: (String) -> Unit) {
    showMessageOnTopOfScreenListener = f
}

fun showMessageOnTopOfScreen(message: String) {
    showMessageOnTopOfScreenListener?.invoke(message)
}
