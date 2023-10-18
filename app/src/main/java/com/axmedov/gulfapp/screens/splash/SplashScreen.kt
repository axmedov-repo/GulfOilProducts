package com.axmedov.gulfapp.screens.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.axmedov.gulfapp.R
import com.axmedov.gulfapp.databinding.ScreenSplashBinding
import com.axmedov.gulfapp.utils.scope
import com.axmedov.gulfapp.utils.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val binding by viewBinding(ScreenSplashBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = binding.scope {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            delay(2000)

            if (Calendar.getInstance().timeInMillis >= 1699470008112) {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToTrialEndScreen())
            } else {
                findNavController().navigate(SplashScreenDirections.actionSplashScreenToProductsScreen())
            }
        }
    }
}