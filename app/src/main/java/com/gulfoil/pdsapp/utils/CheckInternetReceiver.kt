package com.gulfoil.pdsapp.utils

/**
 * CreatedBy: Abdulaziz Akhmedov
 */

//class CheckInternetReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent) {
//        val flashBar =
//            Flashbar.Builder(context as MainActivity).gravity(Flashbar.Gravity.BOTTOM)
//                .title("Connection Alert")
//                .titleSizeInSp(18f).message("You are not connected to the internet!")
//                .messageSizeInSp(16f).showIcon().duration(5000).icon(R.drawable.ic_alert)
//                .iconColorFilterRes(R.color.white).backgroundColorRes(R.color.colorErrorRed)
//                .enterAnimation(
//                    FlashAnim.with(App.instance).animateBar().duration(750).alpha().overshoot()
//                ).exitAnimation(
//                    FlashAnim.with(App.instance).animateBar().duration(400).accelerateDecelerate()
//                ).build()
//        if (!isConnected()) {
//            flashBar.show()
//        } else {
//            flashBar.dismiss()
//        }
//    }
//}
