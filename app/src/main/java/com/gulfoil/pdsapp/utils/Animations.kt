package com.gulfoil.pdsapp.utils

import android.view.View

fun View.blindAnimation() {
    this.animate().setDuration(1000).rotationY(90f).start()
}
